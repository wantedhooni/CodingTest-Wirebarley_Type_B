package com.wirebarley.exchangerate.service.dto;

import java.util.Map;

import com.wirebarley.exchangerate.type.RemittanceCountry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResExchangeRateDto {

	private Boolean success;
	private String terms;
	private String privacy;
	private Long timestamp;
	private RemittanceCountry source;
	private Map<String, Float> quotes;
	private Error error;
	
	@Override
	public String toString() {
		return "ExchangeRateDto [success=" + success + ", terms=" + terms + ", privacy=" + privacy + ", timestamp="
				+ timestamp + ", source=" + source + ", quotes=" + quotes + "]";
	}

	@Getter
	@Setter
	public static class Error{
		private int code;
		private String info;
	}
	
}
