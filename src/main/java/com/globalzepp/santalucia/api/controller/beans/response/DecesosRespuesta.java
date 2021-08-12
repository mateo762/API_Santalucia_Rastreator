package com.globalzepp.santalucia.api.controller.beans.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DecesosRespuesta {

	@ApiModelProperty(notes = "Valores Posibles: True: Búsqueda exitosa / False: Error en Búsqueda")
	private Boolean success;
	@ApiModelProperty(notes = "Códigos existentes:\r\n0: Búsqueda exitosa\r\n1: Datos requeridos\r\n2: Código Postal no válido\r\n9999: Error General")
	private Integer error;
	private String messageError;
	private List<DecesosRespuestaItem> resultados = new ArrayList<>();
	@ApiModelProperty(notes = "Teléfono de contacto para el cliente interesado")
	private String telefono;
	@ApiModelProperty(notes = "Linkde Call me Back para el cliente interesado")
	private String cmbUrl;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public List<DecesosRespuestaItem> getResultados() {
		return resultados;
	}

	public void setResultados(List<DecesosRespuestaItem> resultados) {
		this.resultados = resultados;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCmbUrl() {
		return cmbUrl;
	}

	public void setCmbUrl(String cmbUrl) {
		this.cmbUrl = cmbUrl;
	}

	
}
