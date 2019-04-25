package com.wirebarley.exchangerate.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExchangeRateNavController {

	@GetMapping(value= {"/", "/index"})
	public String index(Map<String, Object> model) {
		return "index";
	}
}
