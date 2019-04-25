package com.wirebarley.exchangerate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wirebarley.exchangerate.controller.req.ExchangeRateReq;
import com.wirebarley.exchangerate.controller.res.CodeResponse;
import com.wirebarley.exchangerate.controller.res.ExchangeRateRes;
import com.wirebarley.exchangerate.service.ExchangeRateService;
import com.wirebarley.exchangerate.service.ExchangeRateServiceImpl;
import com.wirebarley.exchangerate.type.ReceptionCountry;
import com.wirebarley.exchangerate.type.RemittanceCountry;

@RestController
public class ExchangeRateRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ExchangeRateRestController.class);
	
	@Autowired
	private ExchangeRateService exchangeRateSvc;
	
	@RequestMapping("/getReceptionCountrys")
	public @ResponseBody List<CodeResponse> getReceptionCountrys(){
		return Arrays.stream(ReceptionCountry.values()).map(o-> {
			return new CodeResponse(o.getCode(), o.getLabel());
		}).collect(Collectors.toList());
	}
	
	
	@RequestMapping("/getRemittanceCountrys")
	public @ResponseBody List<CodeResponse> getRemittanceCountrys(){
		return Arrays.stream(RemittanceCountry.values()).map(o-> {
			return new CodeResponse(o.getCode(), o.getLabel());
		}).collect(Collectors.toList());
	}
	
	
	@RequestMapping("/getExchangeRate")
	public @ResponseBody ExchangeRateRes getExchangeRate(@Valid ExchangeRateReq req, BindingResult result ) throws Exception{
		if(result.hasErrors()) {
			throw new Exception(result.getFieldError().getDefaultMessage()); 
		}
		
		return exchangeRateSvc.getExchangeRate(req.getRemittanceCountry(), req.getReceptionCountry());
	}
	
	@RequestMapping("/calcReceptionPrice")
	public @ResponseBody ExchangeRateRes calcReceptionPrice(@Valid ExchangeRateReq req, BindingResult result) throws Exception{
		if(result.hasErrors()) {
			throw new Exception(result.getFieldError().getDefaultMessage()); 
		}
		
		if(req.getPrice() == null ||  req.getPrice() <0 ||  req.getPrice() > 10000) {
			throw new Exception("송금액이 바르지 않습니다");
		}
		
		return exchangeRateSvc.calcReceptionPrice(req.getRemittanceCountry(), req.getReceptionCountry(), req.getPrice());
	}
	
	@ExceptionHandler
	public String handle(Exception e) {
		e.printStackTrace();
		log.error("{}", e);
		return e.getMessage();
	}
}
