package com.wirebarley.exchangerate.service;

import com.wirebarley.exchangerate.controller.res.ExchangeRateRes;
import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;

public interface ExchangeRateService {

	/**
	 * 환율을 반환한다.
	 * 
	 * @param remittanceCountry 송금국가
	 * @param receptionCountry 수취국가
	 * @return
	 * @throws Exception
	 */
	public ExchangeRateRes getExchangeRate(RemittanceCountry remittanceCountry, ReceptionCountry receptionCountry) throws Exception;
	
	/**
	 * 수취금액을 반환한다.
	 * 
	 * @param remittanceCountry 송금국가
	 * @param receptionCountry 수취국가
	 * @param receptionPrice 송금액
	 * @return
	 * @throws Exception
	 */
	public ExchangeRateRes calcReceptionPrice(RemittanceCountry remittanceCountry, ReceptionCountry receptionCountry, Float receptionPrice) throws Exception;
}
