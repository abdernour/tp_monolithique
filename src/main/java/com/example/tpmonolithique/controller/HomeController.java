package com.example.tpmonolithique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Redirige la page d'accueil vers /produits
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/produits";
    }
}