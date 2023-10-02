package com.seguridad.seguridad.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @GetMapping("/custom-login")
    String login() {
        return "custom-login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Obtiene el objeto Authentication del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtiene el nombre de usuario del usuario actual
        String username = authentication.getName();

        System.out.println("Nombre de usuario: " + username);

        // Invalida la sesión del usuario actual
        request.getSession().invalidate();

        // Redirige al usuario a la página de inicio de sesión
        return "redirect:/login";
    }

}
