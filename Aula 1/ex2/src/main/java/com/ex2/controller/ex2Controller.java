package com.ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ex2")
public class ex2Controller {
	
	@GetMapping
	public String ex2Show() {
		return "Meu objetivo de aprendizagem essa semana é pegar uma boa base de Spring e continuar estudando Java-POO.";
	}
	
}