package com.globalzepp.santalucia.api.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.globalzepp.santalucia.api.controller.beans.request.DecesosPeticion;
import com.globalzepp.santalucia.api.controller.beans.request.HogarPeticion;
import com.globalzepp.santalucia.api.controller.beans.response.DecesosRespuesta;
import com.globalzepp.santalucia.api.controller.beans.response.DecesosRespuestaItem;
import com.globalzepp.santalucia.api.controller.beans.response.HogarRespuesta;
import com.globalzepp.santalucia.api.controller.beans.response.HogarRespuestaItem;
import com.globalzepp.santalucia.api.integration.SantaLuciaService;

@Service
public class SearchService {

	@Autowired
	SantaLuciaService santaLuciaService;

	@Autowired
	private Environment env;
	
	public DecesosRespuesta searchDecesos(DecesosPeticion decesosPeticion) {
		DecesosRespuesta decesosResponse = new DecesosRespuesta();
		try {
			String requeridos = decesosPeticion.validarRequeridos();
			if (requeridos.length() > 0) {
				decesosResponse.setSuccess(false);
				decesosResponse.setError(1);
				decesosResponse.setMessageError("Los siguientes datos son requeridos: " + requeridos);
				return decesosResponse;
			}
			decesosPeticion.completarDatos(env);
			List<DecesosRespuestaItem> resultados = new ArrayList<>();
			JSONArray resultado = santaLuciaService.sendDecesosRequests(decesosPeticion);
			for (Object o : resultado) {
				if (o instanceof JSONObject) {
					JSONObject res = (JSONObject) o;
					
					if (res.has("error")) {
						decesosResponse.setSuccess(false);
						Integer error = res.getInt("error");
						decesosResponse.setError(error);
						switch (error) {
						case 2:
							decesosResponse.setMessageError("El código postal enviádo no es válido o no corresponde a una localidad con cobertura");
							break;
						default:
							break;
						}
						return decesosResponse;
					}
					
					try {
						Integer modalidad = res.getInt("modalidad");
						String tipoPrima = null;
						switch (modalidad) {
						case 1:
							tipoPrima = "NIVELADA";
							break;
						case 5:
							tipoPrima = "MIXTA";
							break;
						default:
							break;
						}
						if (tipoPrima != null) {
							DecesosRespuestaItem item = new DecesosRespuestaItem();
							item.setTipoPrima(tipoPrima);
							JSONObject anual = (JSONObject) res.getJSONArray("fraccionamientos").get(0);
							item.setPrimaTotalAnual(anual.getBigDecimal("primaTotalAnual"));
							resultados.add(item);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			decesosResponse.setResultados(resultados);
			decesosResponse.setTelefono(env.getProperty("decesos.respuesta.telefono"));
			decesosResponse.setCmbUrl(env.getProperty("decesos.respuesta.cmburl"));
			decesosResponse.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			decesosResponse.setSuccess(false);
			decesosResponse.setError(9999);
			decesosResponse.setMessageError("Error General. Contáctese con el administrador");
		}

		return decesosResponse;
	}

	public HogarRespuesta searchHogar(HogarPeticion hogarPeticion) {
		HogarRespuesta hogarRespuesta = new HogarRespuesta();
		try {
			String requeridos = hogarPeticion.validarRequeridos();
			if (requeridos.length() > 0) {
				hogarRespuesta.setSuccess(false);
				hogarRespuesta.setError(1);
				hogarRespuesta.setMessageError("Los siguientes datos son requeridos: " + requeridos);
				return hogarRespuesta;
			}
			hogarPeticion.completarDatos(env);
			List<HogarRespuestaItem> resultados = new ArrayList<>();
			JSONArray resultado = santaLuciaService.sendHogarRequests(hogarPeticion);
			for (Object o : resultado) {
				if (o instanceof JSONObject) {
					JSONObject res = (JSONObject) o;
					try {
						
						if (res.has("error")) {
							hogarRespuesta.setSuccess(false);
							Integer error = res.getInt("error");
							hogarRespuesta.setError(error);
							switch (error) {
							case 2:
								hogarRespuesta.setMessageError("El código postal enviádo no es válido o no corresponde a una localidad con cobertura");
								break;
							default:
								break;
							}
							return hogarRespuesta;
						}
						
						Integer modalidad = res.getInt("modalidad");
						String tipoProducto = null;
						switch (modalidad) {
						case 8:
							tipoProducto = "BASICO";
							break;
						default:
							break;
						}
						if (tipoProducto != null) {
							HogarRespuestaItem item = new HogarRespuestaItem();
							item.setTipoProducto(tipoProducto);
							JSONObject anual = (JSONObject) res.getJSONArray("fraccionamientos").get(0);
							item.setPrimaTotalAnualFraccionadoAnual(anual.getBigDecimal("primaTotalAnual"));
							JSONObject semestral = (JSONObject) res.getJSONArray("fraccionamientos").get(1);
							item.setPrimaTotalAnualFraccionadoSemestral(semestral.getBigDecimal("primaTotalAnual"));
							JSONObject trimestral = (JSONObject) res.getJSONArray("fraccionamientos").get(2);
							item.setPrimaTotalAnualFraccionadoTrimestral(trimestral.getBigDecimal("primaTotalAnual"));
							resultados.add(item);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			hogarRespuesta.setResultados(resultados);
			hogarRespuesta.setTelefono(env.getProperty("hogar.respuesta.telefono"));
			hogarRespuesta.setCmbUrl(env.getProperty("hogar.respuesta.cmburl"));
			hogarRespuesta.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			hogarRespuesta.setSuccess(false);
			hogarRespuesta.setError(9999);
			hogarRespuesta.setMessageError("Error General. Contáctese con el administrador");
		}

		return hogarRespuesta;
	}

}
