package com.wirebarley.exchangerate.controller.res;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ExchangeRateRes {

	private RemittanceCountry remittanceCountry;
	private ReceptionCountry receptionCountry;
	private String exchangeRate;
	private String calcPrice;
	private String receptionPrice;
	
	@JsonSerialize
	public String getReceptionCountryCode() {
		return receptionCountry.getCode();
	}
	
	@JsonSerialize
	public String getRemittanceCountryCode() {
		return remittanceCountry.getCode();
	}

	@Override
	public String toString() {
		return "ExchangeRateRes [remittanceCountry=" + remittanceCountry + ", receptionCountry=" + receptionCountry
				+ ", exchangeRate=" + exchangeRate + ", calcPrice=" + calcPrice + ", receptionPrice=" + receptionPrice
				+ "]";
	}
	
	
}
