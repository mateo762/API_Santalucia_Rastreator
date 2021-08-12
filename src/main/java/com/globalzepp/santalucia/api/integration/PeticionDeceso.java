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
                "      \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\"><strong>SANTA LUCÍA, S.A., Compañía de Seguros y Reaseguros</strong> (en adelante, santalucía) te informa que tratará tus datos personales que nos facilites con la finalidad de atender la solicitud efectuada y emitir el presupuesto solicitado.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">El hecho de facilitar los datos que te sean solicitados durante el proceso supone su consentimiento a la finalidad de tratamiento de datos descrita.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Adicionalmente, y en caso de que prestes consentimiento mediante la marcación de la casilla al efecto, santalucía tratará tus datos para el envío de publicidad y promoción de cualquier tipo de productos y servicios comercializados por santalucía (por cualquier medio de comunicación), en base a la elaboración de tu perfil que será confeccionado con fuentes internas y enriquecido con datos externos, con la finalidad de introducir mejoras y presentarte ofertas adecuadas a tus necesidades.</p>\",\n" +
                "      \"aceptacion\": \"S\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"concepto\": 484,\n" +
                "      \"descripcionConcepto\": \"<p style=\\\"text-indent:0pt; \\\">La base jurídica para los tratamientos de tus datos personales descritos se encuentra en la propia gestión y desarrollo de la relación jurídica existente entre ti y santalucía y en el consentimiento que te ha sido solicitado.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Santalucía te informa que puedes ejercitar tus derechos de acceso, rectificación, supresión, oposición, limitación del tratamiento y portabilidad, así como oponerte al tratamiento de tus datos con fines promocionales, dirigiéndote a santalucía, mediante un escrito acompañado de tu DNI o documento equivalente, por las dos caras, que deberás remitir a Plaza de España, nº 15, 28008 Madrid a la atención del Departamento de Seguridad Informática o bien a arcolopd@santalucia.es</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Puedes contactar con nuestro Delegado de Protección de Datos en la siguiente dirección: dpo@santalucía.es</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Dispones de información completa sobre protección de datos en www.santalucia.es, en el apartado de Política de Privacidad y Cookies, que te aconsejamos consultar.</p><p>&nbsp;</p><p style=\\\"text-indent:0pt; \\\">Este proyecto de seguro ha sido elaborado conforme a los datos facilitados por el solicitante y las primas calculadas según las tarifas vigentes en el momento de su emisión. Tiene un carácter meramente informativo, no siendo vinculante como propuesta de seguro, ni constituye un documento contractual. La contratación del seguro y su precio quedan supeditados a la aceptación expresa por parte de SANTA LUCÍA S.A., Compañía de Seguros y Reaseguros (en adelante santalucía), condicionada a la información sobre las características del riesgo a asegurar, contenida en la solicitud de seguro y a su verificación.</p>\",\n" +
                "      \"aceptacion\": \"N\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
    }


    public JSONObject getJson(){
        return jsonData;
    }
}
