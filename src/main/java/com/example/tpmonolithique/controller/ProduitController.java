package com.example.tpmonolithique.controller;

import com.example.tpmonolithique.entity.Produit;
import com.example.tpmonolithique.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {
    
    @Autowired
    private ProduitService produitService;
    
 // Afficher la liste des produits avec pagination et tri
    @GetMapping
    public String listeProduits(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateAjout") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model) {
        
        Page<Produit> produitsPage = produitService.getProduitsPage(page, size, sortBy, sortDir);
        
        model.addAttribute("produits", produitsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", produitsPage.getTotalPages());
        model.addAttribute("totalItems", produitsPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        
        return "produits";
    }
    
    // Afficher le formulaire d'ajout
    @GetMapping("/nouveau")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("mode", "ajout"); // sets mode  to add mode so html knows its not edit mode
        return "form";
    }
    
    // Traiter l'ajout d'un produit
    @PostMapping("/ajouter")
    public String ajouterProduit(
            @Valid @ModelAttribute("produit") Produit produit,
            BindingResult result, // contains valkidation errors
            Model model,
            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("mode", "ajout");
            return "form";
        }
        
        produitService.ajouterProduit(produit);
        redirectAttributes.addFlashAttribute("successMessage", 
            "Le produit '" + produit.getNom() + "' a été ajouté avec succès !");
        
        return "redirect:/produits";
    }
    
    // Afficher le formulaire de modification
    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Produit produit = produitService.getProduitById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
            model.addAttribute("produit", produit);
            model.addAttribute("mode", "modification");// form with data prefilled 
            return "form";
            
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/produits";
        }
    }
    
    // Traiter la modification d'un produit
    @PostMapping("/modifier/{id}")
    public String modifierProduit(
            @PathVariable Long id,
            @Valid @ModelAttribute("produit") Produit produit,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("mode", "modification");
            return "form";
        }
        
        try {
            produitService.modifierProduit(id, produit);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Le produit '" + produit.getNom() + "' a été modifié avec succès !");
            return "redirect:/produits";
            
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/produits";
        }
    }
    
    // Supprimer un produit
    @GetMapping("/supprimer/{id}")
    public String supprimerProduit(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Produit produit = produitService.getProduitById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            
            produitService.supprimerProduit(id);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Le produit '" + produit.getNom() + "' a été supprimé avec succès !");
            
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        
        return "redirect:/produits";
    }
}