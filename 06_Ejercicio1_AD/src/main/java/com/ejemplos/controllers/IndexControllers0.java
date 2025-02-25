package com.ejemplos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//marca la clase oo un comntrolador d spring
@Controller
public class IndexControllers0 {

 @GetMapping({"index","/","/home"})
 public String index() {
	return ("index");
 }
 
 
 
 
 
 
	
}
