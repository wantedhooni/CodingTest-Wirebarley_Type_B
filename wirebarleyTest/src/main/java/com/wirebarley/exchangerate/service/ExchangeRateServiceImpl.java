package com.wirebarley.exchangerate.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wirebarley.exchangerate.controller.res.ExchangeRateRes;
import com.wirebarley.exchangerate.service.dto.ResExchangeRateDto;
import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;


@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	private static final Logger log = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	private static  final String PREFIX_CODE = "USD";
	@Value("${currencylayer.url}")
	private String currencylayerUrl;
	
	@Value("${currencylayer.key}")
	private String apiKey;
	
	@Override
	public ExchangeRateRes getExchangeRate(RemittanceCountry remittanceCountry, ReceptionCountry receptionCountry) throws Exception {
		
		Map<String, Float>  quotes = getExchangeRate();
		BigDecimal  remittanceCountryRate = new BigDecimal(quotes.get(PREFIX_CODE + remittanceCountry.getCode()));
		BigDecimal  receptionCountryRate = new BigDecimal(quotes.get(PREFIX_CODE + receptionCountry.getCode()));
		
		
		ExchangeRateRes res = new ExchangeRateRes();
		res.setReceptionCountry(receptionCountry);
		res.setRemittanceCountry(remittanceCountry);
		res.setExchangeRate(receptionCountryRate.divide(remittanceCountryRate, 2, BigDecimal.ROUND_DOWN).floatValue());
		
		return res;
	}
	
	@Override
	public ExchangeRateRes calcReceptionPrice(RemittanceCountry remittanceCountry, ReceptionCountry receptionCountry, Float receptionPrice) throws Exception {
		ExchangeRateRes res = getExchangeRate(remittanceCountry, receptionCountry);
		res.setReceptionPrice(receptionPrice);
		res.setCalcPrice(new BigDecimal(receptionPrice * res.getExchangeRate()).setScale(2, BigDecimal.ROUND_DOWN).floatValue());
		return res;
	}
	
	/**
	 * currencylayer의 RestApi의 값을 반환한다.
	 * USD 기준의 환율을 반환한다.
	 * 통신중 장애가 있을 경우 Exception을 발생시킨다.
	 * @return
	 * @throws Exception
	 */
	private Map<String, Float> getExchangeRate() throws Exception {
		
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(currencylayerUrl)
				.queryParam("access_key", apiKey)
				.queryParam("currencies", getCurrenciesParam());
		
		RestTemplate restTemplate = new RestTemplate();
		ResExchangeRateDto dto
		  = restTemplate.getForObject(builder.toUriString(), ResExchangeRateDto.class);
		log.debug("{}", dto);
		if(!dto.getSuccess()) {
			throw new Exception(dto.getError().getCode() + "-" + dto.getError().getInfo());
		}
		return dto.getQuotes();
	}
	
	/**
	 * 
	 * @return String
	 */
	private String getCurrenciesParam() {
		Set<String> paramCurrencies = new HashSet<String>();
		paramCurrencies.addAll(Arrays.stream(RemittanceCountry.values()).map(v -> v.getCode()).collect(Collectors.toSet()));
		paramCurrencies.addAll(Arrays.stream(ReceptionCountry.values()).map(v -> v.getCode()).collect(Collectors.toSet()));
		return paramCurrencies.stream().map(v -> v.toString()).collect(Collectors.joining(","));
		
	}
	
}
