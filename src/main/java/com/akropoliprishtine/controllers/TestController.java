package com.akropoliprishtine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

}