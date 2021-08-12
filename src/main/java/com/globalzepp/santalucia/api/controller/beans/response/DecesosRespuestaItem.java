package com.globalzepp.santalucia.api.controller.beans.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class DecesosRespuestaItem {

	private BigDecimal primaTotalAnual;
	@ApiModelProperty(notes = "Valores Posibles:\r\n NIVELADA\r\n MIXTA", required = true)
	private String tipoPrima;

	public DecesosRespuestaItem() {
		super();
	}

	public BigDecimal getPrimaTotalAnual() {
		return primaTotalAnual;
	}

	public void setPrimaTotalAnual(BigDecimal primaTotalAnual) {
		this.primaTotalAnual = primaTotalAnual;
	}

	public String getTipoPrima() {
		return tipoPrima;
	}

	public void setTipoPrima(String tipoPrima) {
		this.tipoPrima = tipoPrima;
	}

}
