package com.wirebarley.exchangerate.controller.req;

import javax.validation.constraints.NotNull;

import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateReq {

	@NotNull(message="송금국가는 필수값입니다.")
	private RemittanceCountry remittanceCountry;
	@NotNull(message="수취국가는 필수값입니다.")
	private ReceptionCountry receptionCountry;
	
	private Float price;
}
