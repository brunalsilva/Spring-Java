package com.ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ex1")
public class ex1Controller {
	
	@GetMapping
	public String ex1Show() {
		return "Habilidade de persistência, orientação aos detalhes e orientação ao futuro.";
	}
	
}