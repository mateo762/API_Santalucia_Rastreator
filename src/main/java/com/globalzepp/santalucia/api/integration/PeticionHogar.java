package com.globalzepp.santalucia.api.integration;

import org.json.JSONObject;

import com.globalzepp.santalucia.api.controller.beans.request.HogarPeticion;

public class PeticionHogar {

    private JSONObject jsonData;

    public PeticionHogar(HogarPeticion hogarPeticion, JSONObject localidad){
        this.jsonData = new JSONObject("{\n" +
                "    \"pagina\": \"INFPER\",\n" +
                "    \"causasControles\": null,\n" +
                "    \"datosSuplemento\": null,\n" +
                "    \"guardar\": false,\n" +
                "    \"ramo\": 6,\n" +
                "    \"clausulasGDPR\": [\n" +
                "        {\n" +
                "            \"concepto\": 483,\n" +
                "            \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\"><strong>SANTA LUCÍA, S.A., Compañía de Seguros y Reaseguros<\\/strong> (en adelante, santalucía) te informa que tratará tus datos personales que nos facilites con la finalidad de atender la solicitud efectuada y emitir el presupuesto solicitado.<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">El hecho de facilitar los datos que te sean solicitados durante el proceso supone su consentimiento a la finalidad de tratamiento de datos descrita.<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">Adicionalmente, y en caso de que prestes consentimiento mediante la marcación de la casilla al efecto, santalucía tratará tus datos para el envío de publicidad y promoción de cualquier tipo de productos y servicios comercializados por santalucía (por cualquier medio de comunicación), en base a la elaboración de tu perfil que será confeccionado con fuentes internas y enriquecido con datos externos, con la finalidad de introducir mejoras y presentarte ofertas adecuadas a tus necesidades.<\\/p>\",\n" +
                "            \"aceptacion\": \"S\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"concepto\": 484,\n" +
                "            \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\">La base jurídica para los tratamientos de tus datos personales descritos se encuentra en la propia gestión y desarrollo de la relación jurídica existente entre ti y santalucía y en el consentimiento que te ha sido solicitado.<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">Santalucía te informa que puedes ejercitar tus derechos de acceso, rectificación, supresión, oposición, limitación del tratamiento y portabilidad, así como oponerte al tratamiento de tus datos con fines promocionales, dirigiéndote a santalucía, mediante un escrito acompañado de tu DNI o documento equivalente, por las dos caras, que deberás remitir a Plaza de España, nº 15, 28008 Madrid a la atención del Departamento de Seguridad Informática o bien a arcolopd@santalucia.es<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">Puedes contactar con nuestro Delegado de Protección de Datos en la siguiente dirección: dpo@santalucía.es<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">Dispones de información completa sobre protección de datos en www.santalucia.es, en el apartado de Política de Privacidad y Cookies, que te aconsejamos consultar.<\\/p><p>&nbsp;<\\/p><p style=\\\"text-indent:0pt; \\\">Este proyecto de seguro ha sido elaborado conforme a los datos facilitados por el solicitante y las primas calculadas según las tarifas vigentes en el momento de su emisión. Tiene un carácter meramente informativo, no siendo vinculante como propuesta de seguro, ni constituye un documento contractual. La contratación del seguro y su precio quedan supeditados a la aceptación expresa por parte de SANTA LUCÍA S.A., Compañía de Seguros y Reaseguros (en adelante santalucía), condicionada a la información sobre las características del riesgo a asegurar, contenida en la solicitud de seguro y a su verificación.<\\/p>\",\n" +
                "            \"aceptacion\": \"N\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"solicitante\": {\n" +
                "        \"tipoPersona\": \"F\",\n" +
                "        \"fechaNacimiento\": \"" + hogarPeticion.getFechaNacimiento() + "\",\n" +
                "        \"nombre\": \"Pepe\",\n" +
                "        \"email\": \"holaquetal@gmail.com\"\n" +
                "    },\n" +
                "    \"datosAgencia\": {\n" +
                "        \"auxiliar\": \"2AB7\",\n" +
                "        \"agencia\": 194\n" +
                "    },\n" +
                "    \"datosHogar\": {\n" +
                "        \"tipoVivienda\": \"" + hogarPeticion.getVivienda() + "\",\n" +
                "        \"importeContenido\": " + hogarPeticion.getImporteContenido() + ",\n" +
                "        \"liquidosInflamables\": \"N\",\n" +
                "        \"cesionDerechos\": \"N\",\n" +
                "        \"ocupacionVivienda\": \"H\",\n" +
                "        \"numeroBanyos\": " +  hogarPeticion.getAseos() + ",\n" +
                "        \"usoVivienda\": \"" + hogarPeticion.getUsoVivienda() + "\",\n" +
                "        \"importeDanyosTerceros\": 300000,\n" +
                "        \"prestamoHipotecario\": \"" + (hogarPeticion.getPrestamoHipotecario()?"S":"N") + "\",\n" +
                "        \"metrosVivienda\": \"" + hogarPeticion.getMetrosVivienda() + "\",\n" +
                "        \"caracterActua\": \"" + hogarPeticion.getPropietario() + "\",\n" +
                "        \"importeContinente\": " + hogarPeticion.getImporteContinente() + ",\n" +
                "        \"anyoConstruccion\": \"" + hogarPeticion.getAnyoConstruccion() + "\",\n" +
                "        \"mascotas\": \"N\",\n" +
                "        \"domicilioRiesgo\": {\n" +
                "            \"codigoPoblacion\": \"" + localidad.getString("codigoPoblacion") + "\",\n" +
                "            \"puerta\": \"\",\n" +
                "            \"piso\": \"4d\",\n" +
                "            \"codigoPostal\": \"" + hogarPeticion.getCodigoPostal() + "\",\n" +
                "            \"numero\": \"4\",\n" +
                "            \"restoDatos\": null,\n" +
                "            \"tipoVia\": \"CL\",\n" +
                "            \"escalera\": null,\n" +
                "            \"viaNormalizada\": true,\n" +
                "            \"codigoProvincia\": \"" + localidad.getInt("codigoProvincia") + "\",\n" +
                "            \"seccionCensal\": \"" + localidad.getString("seccionCensal") + "\",\n" +
                "            \"provincia\": \"" + localidad.getString("provincia").toUpperCase() + "\",\n" +
                "            \"domicilio\": \"\",\n" +
                "            \"localidad\": \"" + localidad.getString("localidad") + "\",\n" +
                "            \"portal\": null,\n" +
                "            \"bloque\": null,\n" +
                "            \"poblacionNormalizada\": true,\n" +
                "            \"codigoTipoPostal\": null\n" +
                "        },\n" +
                "        \"nucleoUrbano\": \"" + (hogarPeticion.getNucleoUrbano()?"S":"N") + "\",\n" +
                "        \"estructuraMadera\": \"" + (hogarPeticion.getMadera()?"S":"N") + "\",\n" +
                "        \"metrosTrastero\": 0,\n" +
                "        \"metrosGaraje\": 0,\n" +
                "        \"importePrestamoHipoteca\": 0\n" +
                "    },\n" +
                "    \"usuario\": {\n" +
                "        \"autorizacion\": \"4\",\n" +
                "        \"numero\": 99999,\n" +
                "        \"canal\": \"W\"\n" +
                "    },\n" +
                "    \"telefono2\": \"\",\n" +
                "    \"firmaConsentimientos\": null,\n" +
                "    \"email\": \"holaquetal@gmail.com\",\n" +
                "    \"trazaVentaSalesforce\": {\n" +
                "        \"prospectId\": \"\",\n" +
                "        \"clienteId\": 0,\n" +
                "        \"oportunidadId\": \"\"\n" +
                "    }\n" +
                "}");
    }

    public JSONObject getJson() {
        return jsonData;
    }
}
