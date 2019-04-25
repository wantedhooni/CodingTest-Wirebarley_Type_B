package com.wirebarley.exchangerate.type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

/**
 * 수취국가 Type
 * @author
 *
 */
public enum ReceptionCountry {

	 KRW("KRW", "한국"),
	 JPY("JPY", "일본"),
	 PHP("PHP", "필리핀");
	
	@Getter
	@JsonSerialize
	private String code;
	@Getter
	@JsonSerialize
	private String label;
	
	ReceptionCountry(String code, String label) {
	this.code = code;
	this.label = label;
	} 
	
	
	
	
}
