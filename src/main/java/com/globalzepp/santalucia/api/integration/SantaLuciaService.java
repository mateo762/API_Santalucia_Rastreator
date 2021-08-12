package com.globalzepp.santalucia.api.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.globalzepp.santalucia.api.controller.beans.request.DecesosPeticion;
import com.globalzepp.santalucia.api.controller.beans.request.HogarPeticion;

@Service
public class SantaLuciaService {

	private String id_token;

	private String getFirstWord(String sentence) {
		String[] aux = sentence.split(" ");
		return aux[0];
	}

	// Metodo auxiliar para rellenar la informacion correspondiente con el código
	// postal
	private void fillInfo(JSONObject json, JSONArray info) {
		JSONObject object = info.getJSONObject(0);
		JSONObject localidades = object.getJSONObject("localidad");
		JSONObject provincias = object.getJSONObject("provincia");
		json.put("localidad", localidades.getString("nombre"));
		json.put("provincia", provincias.getString("descripcion"));
		json.put("codigoLocalidad", localidades.getInt("codigo"));
		json.put("codigoProvincia", provincias.getInt("codigo"));
	}

	// Metodo para obtener el codigo poblacional
	private void fillCodigoPoblacional(JSONObject json, JSONArray info) {
		for (int i = 0; i < info.length(); i++) {
			JSONObject object = info.getJSONObject(i);
			if (object.getString("poblacion").equals(json.getString("localidad"))) {
				json.put("codigoMunicipio", object.getString("codigoMunicipio"));
				json.put("codigoPoblacion", object.getString("codigoPoblacion"));
				break;
			}
		}
	}

	// Metodo para obtener la seccion censal
	private void fillSeccionCensal(JSONObject json) {
		String codigo = json.getString("codigoPoblacion");
		char[] codigoArr = codigo.toCharArray();
		char[] seccionCensal = new char[10];
		for (int i = 0; i < 10; i++) {
			if (i < 5)
				seccionCensal[i] = codigoArr[i];
			else
				seccionCensal[i] = '0';
		}
		json.put("seccionCensal", new String(seccionCensal));
	}

	// Utilizamos este metodo para obtener el token
	private JSONObject getRequest(String url) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JSONObject json = new JSONObject(response.body().toString());
		id_token = json.getString("access_token");
		return new JSONObject(response.body().toString());
	}

	// Utilizamos este metodo cuando la respuesta es un json que empieza por "["
	private JSONArray getRequestArray(String url) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.header("Authorization", "Bearer " + id_token).header("Content-Type", "application/json").build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return new JSONArray(response.body().toString());
	}

	// Utilizamos este metodo cuando la respuesta es un json que empieza por "{"
	private JSONObject postRequestObject(String url, JSONObject json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + id_token)
				.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return new JSONObject(response.body());
	}

	// Utilizamos este metodo cuando la respuesta es un json que empieza por "["
	private JSONArray postRequestArray(String url, JSONObject json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + id_token)
				.POST(HttpRequest.BodyPublishers.ofString(json.toString())).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return new JSONArray(response.body());
	}

	public JSONArray sendDecesosRequests(DecesosPeticion decesosPeticion) throws Exception {

		JSONObject jsonData = new JSONObject();

		// PASO 1: OBTENER EL TOKEN
		getRequest("https://calcular-seguro-decesos.santalucia.es/generate-default-token");

		// PASO 2: RELLENAR LA INFORMACIÓN (provincia y localidad, codigo poblacional y
		// domicilio)
		JSONArray codigoPostalInfo = getRequestArray(
				"https://calcular-seguro-decesos.santalucia.es/localidades?codigoPostal="
						+ decesosPeticion.getCodigoPostal());

		if (codigoPostalInfo.length() == 0) {
			JSONObject error = new JSONObject();
			error.put("error", 2);
			codigoPostalInfo.put(0, error);
			return codigoPostalInfo;
		}

		fillInfo(jsonData, codigoPostalInfo);

		JSONArray infoCodigoPoblacional = getRequestArray(
				"https://calcular-seguro-hogar.santalucia.es/poblaciones?poblacion="
						+ getFirstWord(jsonData.getString("localidad")) + "&codigoProvincia="
						+ jsonData.getInt("codigoProvincia") + "&cantidad=40");
		fillCodigoPoblacional(jsonData, infoCodigoPoblacional);

		fillSeccionCensal(jsonData);

		// PASO 3: PRESUPUESTOS (ENVIAMOS TODOS LOS DATOS(TELEFONO,CORREO...) COMO
		// PARAMETRO)
		PeticionDeceso peticionDeceso = new PeticionDeceso(decesosPeticion, jsonData); // CREA EL JSON QUE DEBEMOS
																						// ENVIAR
																						// A PRESUPUESTOS
		JSONObject presupuestos = postRequestObject("https://calcular-seguro-decesos.santalucia.es/presupuestos",
				peticionDeceso.getJson());

		// PASO 4: MULTITARIFICAR (ENVIAMOS "PRESUPUESTOS" COMO PARAMETRO)
		JSONArray multitarificar = postRequestArray("https://calcular-seguro-decesos.santalucia.es/multitarificar",
				presupuestos);
		System.out.println(multitarificar.toString(4));

		return multitarificar;
		// PASO 5: AGRUPACIONES-SUBGARANTIAS --NO HE PODIDO RESOLVER CUALES DEBERÍAN SER
		// LOS PARAMETROS DE ESTE PASO--
		// JSONArray subagrupaciones =
		// postRequestArray("https://calcular-seguro-decesos.santalucia.es/agrupaciones-subgarantias",presupuestos);
	}

	public JSONArray sendHogarRequests(HogarPeticion hogarPeticion) throws Exception {

		JSONObject jsonData = new JSONObject();

		// PASO 1: OBTENER EL TOKEN
		getRequest("https://calcular-seguro-hogar.santalucia.es/generate-default-token");

		// PASO 2: RELLENAR LA INFORMACIÓN (provincia y localidad, codigo poblacional y
		// domicilio)
		JSONArray codigoPostalInfo = getRequestArray(
				"https://calcular-seguro-decesos.santalucia.es/localidades?codigoPostal="
						+ hogarPeticion.getCodigoPostal());

		if (codigoPostalInfo.length() == 0) {
			JSONObject error = new JSONObject();
			error.put("error", 2);
			codigoPostalInfo.put(0, error);
			return codigoPostalInfo;
		}

		fillInfo(jsonData, codigoPostalInfo);

		JSONArray infoCodigoPoblacional = getRequestArray(
				"https://calcular-seguro-hogar.santalucia.es/poblaciones?poblacion="
						+ getFirstWord(jsonData.getString("localidad")) + "&codigoProvincia="
						+ jsonData.getInt("codigoProvincia") + "&cantidad=40");
		fillCodigoPoblacional(jsonData, infoCodigoPoblacional);

		fillSeccionCensal(jsonData);

		// PASO 3: ENVIAR LA PETICION QUE NOS DEVUELVE EL PRESUPUESTO
		PeticionHogar peticion = new PeticionHogar(hogarPeticion, jsonData);
		JSONObject result = postRequestObject("https://calcular-seguro-hogar.santalucia.es/hogar/presupuestos",
				peticion.getJson());

		// PASO 4: OBTENEMOS LOS PRESUPESTOS (ESTO ES LO QUE TE DA LOS PRECIOS)
		JSONArray response = postRequestArray("https://calcular-seguro-hogar.santalucia.es/hogar/multitarificar",
				result);

		System.out.println(response.toString(4));

		return response;
		// PASO 5: COMPARAR PRODUCTOS (EL REQUEST SIEMPRE ES EL MISMO)
		// String request2 =
		// "{\"productos\":[{\"orden\":0,\"ramo\":6,\"modalidad\":7,\"descripcion\":\"Un
		// seguro con amplias coberturas
		// básicas\",\"partidasBasicas\":[{\"codigo\":\"CTE\",\"descripcion\":\"Continente\"},{\"codigo\":\"CDO\",\"descripcion\":\"Contenido\"},{\"codigo\":\"RCG\",\"descripcion\":\"Responsabilidad
		// Civil\"},{\"codigo\":\"PPG\",\"descripcion\":\"Protección de
		// Pagos\"},{\"codigo\":\"DRA\",\"descripcion\":\"Derrumbe
		// Accidental\"},{\"codigo\":\"CNX\",\"descripcion\":\"Conexión para
		// mantenimiento de
		// vivienda\"}],\"partidasOpcionales\":[]},{\"orden\":1,\"ramo\":6,\"modalidad\":8,\"descripcion\":\"Un
		// seguro con amplias coberturas
		// básicas\",\"partidasBasicas\":[{\"codigo\":\"CTE\",\"descripcion\":\"Continente\"},{\"codigo\":\"CDO\",\"descripcion\":\"Contenido\"},{\"codigo\":\"RCG\",\"descripcion\":\"Responsabilidad
		// Civil\"},{\"codigo\":\"PPG\",\"descripcion\":\"Protección de
		// Pagos\"},{\"codigo\":\"DRA\",\"descripcion\":\"Derrumbe
		// Accidental\"},{\"codigo\":\"PJV\",\"descripcion\":\"Protección Jurídica de la
		// Vivienda\"},{\"codigo\":\"AHB\",\"descripcion\":\"Asistencia del
		// hogar\"},{\"codigo\":\"CNX\",\"descripcion\":\"Conexión para mantenimiento de
		// vivienda\"},{\"codigo\":\"RVC\",\"descripcion\":\"Rot. de elementos
		// Vitrocerámicos
		// cocina\"}],\"partidasOpcionales\":[]},{\"orden\":2,\"ramo\":6,\"modalidad\":10,\"descripcion\":\"La
		// mejor asistencia en tu
		// hogar\",\"partidasBasicas\":[{\"codigo\":\"CTE\",\"descripcion\":\"Continente\"},{\"codigo\":\"CDO\",\"descripcion\":\"Contenido\"},{\"codigo\":\"RCG\",\"descripcion\":\"Responsabilidad
		// Civil\"},{\"codigo\":\"PPG\",\"descripcion\":\"Protección de
		// Pagos\"},{\"codigo\":\"DRA\",\"descripcion\":\"Derrumbe
		// Accidental\"},{\"codigo\":\"JUR\",\"descripcion\":\"Protección Jurídica
		// Integral\"},{\"codigo\":\"AH9\",\"descripcion\":\"Asistencia del Hogar y
		// Bricolaje\"},{\"codigo\":\"CNX\",\"descripcion\":\"Conexión para
		// mantenimiento de
		// vivienda\"}],\"partidasOpcionales\":[{\"codigo\":\"AR1\",\"descripcion\":\"Ampliación
		// Reposición
		// Estética\"}]},{\"orden\":3,\"ramo\":6,\"modalidad\":12,\"descripcion\":\"La
		// mejor asistencia en tu
		// hogar\",\"partidasBasicas\":[{\"codigo\":\"CTE\",\"descripcion\":\"Continente\"},{\"codigo\":\"CDO\",\"descripcion\":\"Contenido\"},{\"codigo\":\"RCG\",\"descripcion\":\"Responsabilidad
		// Civil\"},{\"codigo\":\"PPG\",\"descripcion\":\"Protección de
		// Pagos\"},{\"codigo\":\"DRA\",\"descripcion\":\"Derrumbe
		// Accidental\"},{\"codigo\":\"JUR\",\"descripcion\":\"Protección Jurídica
		// Integral\"},{\"codigo\":\"CNX\",\"descripcion\":\"Conexión para mantenimiento
		// de vivienda\"},{\"codigo\":\"AHP\",\"descripcion\":\"Asistencia del Hogar
		// Premium\"},{\"codigo\":\"BR1\",\"descripcion\":\"Brico
		// Asistencia\"},{\"codigo\":\"TDA\",\"descripcion\":\"Todo Riesgo
		// Accidental\"}],\"partidasOpcionales\":[{\"codigo\":\"AR1\",\"descripcion\":\"Ampliación
		// Reposición Estética\"}]}],\"canal\":\"W\"}";
		// JSONArray finalResponse =
		// postRequestArray("https://calcular-seguro-hogar.santalucia.es/hogar/comparador-productos",new
		// JSONObject(request2));
	}
}
