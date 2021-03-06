package com.globalzepp.santalucia.api.integration;

import java.time.LocalDate;

import org.json.JSONObject;

import com.globalzepp.santalucia.api.controller.beans.request.DecesosPeticion;

public class PeticionDeceso {
    static LocalDate tomorrow = LocalDate.now().plusDays(1);
    private JSONObject jsonData;

    private String getPersona(DecesosPeticion decesosPeticion, JSONObject localidad){
        String aux = "";
        for(int i=0; i < decesosPeticion.getFechasNacimiento().length; i++){
            aux += "{\n" +
                    "        \"persona\": {\n" +
                    "          \"fechaNacimiento\": \"" + decesosPeticion.getFechasNacimiento()[i] + "\",\n" +
                    "          \"codigoNacionalidad\": \"ESP\"\n" +
                    "        },\n" +
                    "        \"codigoPostal\": " + decesosPeticion.getCodigoPostal() + ",\n" +
                    "        \"codigoLocalidad\": " + localidad.getInt("codigoLocalidad") + ",\n" +
                    "        \"localidad\": \"" + localidad.getString("localidad") + "\",\n" +
                    "        \"codigoProvincia\": " + localidad.getInt("codigoProvincia") + ",\n" +
                    "        \"provincia\": \"" + localidad.getString("provincia") + "\",\n" +
                    "        \"paisRepatriacion\": \"ESP\"\n" +
                    "      }";
            if(i == decesosPeticion.getFechasNacimiento().length -1){
                aux += "\n";
            }else{
                aux += ",\n";
            }
        }
        return aux;
    }

    public PeticionDeceso(DecesosPeticion decesosPeticion, JSONObject localidad){
        this.jsonData = new JSONObject("{\n" +
                "  \"usuario\": {\n" +
                "    \"numero\": 99999,\n" +
                "    \"canal\": \"W\",\n" +
                "    \"autorizacion\": \"4\"\n" +
                "  },\n" +
                "  \"datosAgencia\": {\n" +
                "    \"agencia\": 194,\n" +
                "    \"auxiliar\": \"2AB7\"\n" +
                "  },\n" +
                "  \"datosContratacion\": {\n" +
                "    \"mascotas\": [],\n" +
                "    \"fechaEfecto\": \"" + tomorrow + "\",\n" +
                "    \"asegurados\": [\n" + getPersona(decesosPeticion, localidad) +
                "    ],\n" +
                "    \"tomador\": {\n" +
                "      \"email\": \"holaquetal@gmail.com\",\n" +
                "      \"telefono\": \"636762549\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"ramo\": 33,\n" +
                "  \"trazaVentaSalesforce\": {\n" +
                "    \"oportunidadId\": \"\",\n" +
                "    \"prospectId\": \"\",\n" +
                "    \"clienteId\": 0\n" +
                "  },\n" +
                "  \"datosSuplemento\": null,\n" +
                "  \"pagina\": \"INFPER\",\n" +
                "  \"firmaConsentimientos\": null,\n" +
                "  \"canal\": \"W\",\n" +
                "  \"causasControles\": null,\n" +
                "  \"email\": \"holaquetal@gmail.com\",\n" +
                "  \"telefono\": \"636762549\",\n" +
                "  \"solicitante\": {\n" +
                "    \"nombre\": \"Mateo\",\n" +
                "    \"telefono\": \"636762549\",\n" +
                "    \"email\": \"holaquetal@gmail.com\"\n" +
                "  },\n" +
                "  \"clausulasGDPR\": [\n" +
                "    {\n" +
                "      \"concepto\": 483,\n" +
                "      \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\"><strong>SANTA LUC??A, S.A., Compa????a de Seguros y Reaseguros</strong> (en adelante, santaluc??a) te informa que tratar?? tus datos personales que nos facilites con la finalidad de atender la solicitud efectuada y emitir el presupuesto solicitado.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">El hecho de facilitar los datos que te sean solicitados durante el proceso supone su consentimiento a la finalidad de tratamiento de datos descrita.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Adicionalmente, y en caso de que prestes consentimiento mediante la marcaci??n de la casilla al efecto, santaluc??a tratar?? tus datos para el env??o de publicidad y promoci??n de cualquier tipo de productos y servicios comercializados por santaluc??a (por cualquier medio de comunicaci??n), en base a la elaboraci??n de tu perfil que ser?? confeccionado con fuentes internas y enriquecido con datos externos, con la finalidad de introducir mejoras y presentarte ofertas adecuadas a tus necesidades.</p>\",\n" +
                "      \"aceptacion\": \"S\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"concepto\": 484,\n" +
                "      \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\">La base jur??dica para los tratamientos de tus datos personales descritos se encuentra en la propia gesti??n y desarrollo de la relaci??n jur??dica existente entre ti y santaluc??a y en el consentimiento que te ha sido solicitado.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Santaluc??a te informa que puedes ejercitar tus derechos de acceso, rectificaci??n, supresi??n, oposici??n, limitaci??n del tratamiento y portabilidad, as?? como oponerte al tratamiento de tus datos con fines promocionales, dirigi??ndote a santaluc??a, mediante un escrito acompa??ado de tu DNI o documento equivalente, por las dos caras, que deber??s remitir a Plaza de Espa??a, n?? 15, 28008 Madrid a la atenci??n del Departamento de Seguridad Inform??tica o bien a arcolopd@santalucia.es</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Puedes contactar con nuestro Delegado de Protecci??n de Datos en la siguiente direcci??n: dpo@santaluc??a.es</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Dispones de informaci??n completa sobre protecci??n de datos en www.santalucia.es, en el apartado de Pol??tica de Privacidad y Cookies, que te aconsejamos consultar.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Este proyecto de seguro ha sido elaborado conforme a los datos facilitados por el solicitante y las primas calculadas seg??n las tarifas vigentes en el momento de su emisi??n. Tiene un car??cter meramente informativo, no siendo vinculante como propuesta de seguro, ni constituye un documento contractual. La contrataci??n del seguro y su precio quedan supeditados a la aceptaci??n expresa por parte de SANTA LUC??A S.A., Compa????a de Seguros y Reaseguros (en adelante santaluc??a), condicionada a la informaci??n sobre las caracter??sticas del riesgo a asegurar, contenida en la solicitud de seguro y a su verificaci??n.</p>\",\n" +
                "      \"aceptacion\": \"N\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }


    public JSONObject getJson(){
        return jsonData;
    }
}
