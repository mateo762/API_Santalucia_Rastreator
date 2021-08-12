package com.globalzepp.santalucia.api.controller.beans.request;

import org.springframework.core.env.Environment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DecesosPeticion {

	private Integer codigoPostal;
	private String[] fechasNacimiento;
	private String sexo;
	private String telefono;
	private String email;

	public DecesosPeticion() {
		super();
	}

	@ApiModelProperty(notes = "Formato de Código Postal: 99999", required = true)
	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@ApiModelProperty(notes = "Listado de fechas separadas por coma. Formtado de las fechas 'AAAA-MM-DD'", required = true)
	public String[] getFechasNacimiento() {
		return fechasNacimiento;
	}

	public void setFechasNacimiento(String[] fechasNacimiento) {
		this.fechasNacimiento = fechasNacimiento;
	}

	@ApiModelProperty(notes = "Valores Posibles: HOMBRE / MUJER")
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String validarRequeridos() {
		String requeridos = "";
		if (getCodigoPostal() == null)
			requeridos = "codigoPostal";
		if (getFechasNacimiento() == null || getFechasNacimiento().length == 0)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "fechasNacimiento";
		return requeridos;
	}

	public void completarDatos(Environment env) {
		if (getTelefono() == null)
			setTelefono(env.getProperty("decesos.default.telefono"));
		if (getEmail() == null)
			setEmail(env.getProperty("decesos.default.email"));
	}

}
