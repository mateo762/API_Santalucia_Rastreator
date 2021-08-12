package com.globalzepp.santalucia.api.controller.beans.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class HogarRespuestaItem {

	private BigDecimal primaTotalAnualFraccionadoAnual;
	private BigDecimal primaTotalAnualFraccionadoSemestral;
	private BigDecimal primaTotalAnualFraccionadoTrimestral;
	@ApiModelProperty(notes = "Valores Posibles:\r\n BASICO", required = true)
	private String tipoProducto;

	public HogarRespuestaItem() {
		super();
	}

	public BigDecimal getPrimaTotalAnualFraccionadoAnual() {
		return primaTotalAnualFraccionadoAnual;
	}

	public void setPrimaTotalAnualFraccionadoAnual(BigDecimal primaTotalAnualFraccionadoAnual) {
		this.primaTotalAnualFraccionadoAnual = primaTotalAnualFraccionadoAnual;
	}

	public BigDecimal getPrimaTotalAnualFraccionadoSemestral() {
		return primaTotalAnualFraccionadoSemestral;
	}

	public void setPrimaTotalAnualFraccionadoSemestral(BigDecimal primaTotalAnualFraccionadoSemestral) {
		this.primaTotalAnualFraccionadoSemestral = primaTotalAnualFraccionadoSemestral;
	}

	public BigDecimal getPrimaTotalAnualFraccionadoTrimestral() {
		return primaTotalAnualFraccionadoTrimestral;
	}

	public void setPrimaTotalAnualFraccionadoTrimestral(BigDecimal primaTotalAnualFraccionadoTrimestral) {
		this.primaTotalAnualFraccionadoTrimestral = primaTotalAnualFraccionadoTrimestral;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}
