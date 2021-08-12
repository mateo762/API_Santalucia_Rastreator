package com.globalzepp.santalucia.api.controller.beans.request;

import org.springframework.core.env.Environment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class HogarPeticion {

	@ApiModelProperty(notes = "Valores Posibles:\r\n P = Planta Media\r\n T = Ático\r\n B = Planta Baja\r\n"
			+ "U = Vivienda Unifamiliar\r\n S = Adosado/Pareado", required = true)
	private String vivienda;
	@ApiModelProperty(required = true)
	private Boolean madera;
	@ApiModelProperty(notes = "Formato de Código Postal: 99999", required = true)
	private String codigoPostal;
	@ApiModelProperty(notes = "Valores Posibles:\r\n P = Propietario\r\n I = Inquilino", required = true)
	private String propietario;
	@ApiModelProperty(notes = "Valores Posibles:\r\n P = Principal\r\n A = En alquiler\r\n"
			+ "C = Cedido a terceros\r\n S = Secundaria", required = true)
	private String usoVivienda;
	@ApiModelProperty(required = true)
	private String fechaNacimiento;
	@ApiModelProperty(required = true)
	private Boolean nucleoUrbano;
	@ApiModelProperty(required = true)
	private Boolean prestamoHipotecario;
	@ApiModelProperty(required = true)
	private Integer aseos;
	@ApiModelProperty(required = true)
	private Integer metrosVivienda;
	@ApiModelProperty(required = true)
	private Integer anyoConstruccion;
	@ApiModelProperty(required = true)
	private Integer importeContinente;
	@ApiModelProperty(required = true)
	private Integer importeContenido;

	public HogarPeticion() {
		super();
	}

	public String getVivienda() {
		return vivienda;
	}

	public void setVivienda(String vivienda) {
		this.vivienda = vivienda;
	}

	public Boolean getMadera() {
		return madera;
	}

	public void setMadera(Boolean madera) {
		this.madera = madera;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public String getUsoVivienda() {
		return usoVivienda;
	}

	public void setUsoVivienda(String usoVivienda) {
		this.usoVivienda = usoVivienda;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getNucleoUrbano() {
		return nucleoUrbano;
	}

	public void setNucleoUrbano(Boolean nucleoUrbano) {
		this.nucleoUrbano = nucleoUrbano;
	}

	public Boolean getPrestamoHipotecario() {
		return prestamoHipotecario;
	}

	public void setPrestamoHipotecario(Boolean prestamoHipotecario) {
		this.prestamoHipotecario = prestamoHipotecario;
	}

	public Integer getAseos() {
		return aseos;
	}

	public void setAseos(Integer aseos) {
		this.aseos = aseos;
	}

	public Integer getMetrosVivienda() {
		return metrosVivienda;
	}

	public void setMetrosVivienda(Integer metrosVivienda) {
		this.metrosVivienda = metrosVivienda;
	}

	public Integer getAnyoConstruccion() {
		return anyoConstruccion;
	}

	public void setAnyoConstruccion(Integer anyoConstruccion) {
		this.anyoConstruccion = anyoConstruccion;
	}

	public Integer getImporteContinente() {
		return importeContinente;
	}

	public void setImporteContinente(Integer importeContinente) {
		this.importeContinente = importeContinente;
	}

	public Integer getImporteContenido() {
		return importeContenido;
	}

	public void setImporteContenido(Integer importeContenido) {
		this.importeContenido = importeContenido;
	}

	public String validarRequeridos() {
		String requeridos = "";
		if (getCodigoPostal() == null)
			requeridos = "codigoPostal";
		if (getMadera() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "madera";
		if (getNucleoUrbano() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "nucleoUrbano";
		if (getPrestamoHipotecario() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "prestamoHipotecario";
		if (getAnyoConstruccion() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "anyoConstrucción";
		if (getAseos() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "aseos";
		if (getFechaNacimiento() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "fechaNacimiento";
		if (getImporteContenido() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "importeContenido";
		if (getImporteContinente() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "importeContinente";
		if (getMetrosVivienda() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "metrosVivienda";
		if (getPropietario() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "propietario";
		if (getUsoVivienda() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "usoVivienda";
		if (getVivienda() == null)
			requeridos = ((requeridos.length() > 0) ? requeridos + ", " : "") + "vivienda";
		return requeridos;
	}

	public void completarDatos(Environment env) {
	}
}
