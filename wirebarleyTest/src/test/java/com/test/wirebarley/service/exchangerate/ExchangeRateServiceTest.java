package com.test.wirebarley.service.exchangerate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wirebarley.exchangerate.service.ExchangeRateServiceImpl;
import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ExchangeRateServiceImpl.class})
public class ExchangeRateServiceTest {

	static final Logger log = LoggerFactory.getLogger(ExchangeRateServiceTest.class);
	
	@Autowired private ExchangeRateServiceImpl exchangeRateSvc;
	
	@Test
	public void getExchangeRateTest() throws Exception {
		RemittanceCountry remittanceCountry;
		ReceptionCountry  receptionCountry;
		
		remittanceCountry = RemittanceCountry.USD;
		receptionCountry = ReceptionCountry.KRW;
		log.info("{} {}/{}", exchangeRateSvc.getExchangeRate(remittanceCountry, receptionCountry) ,receptionCountry.getCode(), remittanceCountry.getCode());
		remittanceCountry = RemittanceCountry.AUD;
		receptionCountry = ReceptionCountry.KRW;
		log.info("{} {}/{}", exchangeRateSvc.getExchangeRate(remittanceCountry, receptionCountry) ,receptionCountry.getCode(), remittanceCountry.getCode());
		remittanceCountry = RemittanceCountry.AUD;
		receptionCountry = ReceptionCountry.JPY;
		log.info("{} {}/{}", exchangeRateSvc.getExchangeRate(remittanceCountry, receptionCountry) ,receptionCountry.getCode(), remittanceCountry.getCode());
		remittanceCountry = RemittanceCountry.AUD;
		receptionCountry = ReceptionCountry.PHP;
		log.info("{} {}/{}", exchangeRateSvc.getExchangeRate(remittanceCountry, receptionCountry) ,receptionCountry.getCode(), remittanceCountry.getCode());
	}

	@Test
	public void calcReceptionPriceTest() throws Exception {
		RemittanceCountry remittanceCountry;
		ReceptionCountry  receptionCountry;
		
		remittanceCountry = RemittanceCountry.USD;
		receptionCountry = ReceptionCountry.KRW;
		log.info("{} {}", exchangeRateSvc.calcReceptionPrice(remittanceCountry, receptionCountry, 100f) ,receptionCountry.getCode());
		
		remittanceCountry = RemittanceCountry.AUD;
		receptionCountry = ReceptionCountry.KRW;
		log.info("{} {}", exchangeRateSvc.calcReceptionPrice(remittanceCountry, receptionCountry, 100f), receptionCountry.getCode());
	}
	
}
