package com.seguridad.seguridad.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {
    
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
