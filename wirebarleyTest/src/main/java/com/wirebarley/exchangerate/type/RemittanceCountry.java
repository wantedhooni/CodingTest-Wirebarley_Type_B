package com.wirebarley.exchangerate.type;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;

/**
 * 송금국가 Type
 * @author 
 *
 */
public enum RemittanceCountry {
	USD("USD", "미국"),
	AUD("AUD", "호주");
	
	@Getter
	@JsonSerialize
	private String code;
	@Getter
	@JsonSerialize
	private String label;
	
	RemittanceCountry(String code, String label) {
	this.code = code;
	this.label = label;
	} 
	
}
