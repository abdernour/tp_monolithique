package com.example.tpmonolithique.service;

import com.example.tpmonolithique.entity.Produit;
import com.example.tpmonolithique.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;
    
    // CREATE - Ajouter un produit
    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }
    
    // READ - Tous les produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAjout"));
    }
 // READ - Avec pagination et tri
    public Page<Produit> getProduitsPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") 
            ? Sort.by(sortBy).ascending() 
            : Sort.by(sortBy).descending();
        
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return produitRepository.findAll(pageable);
    }
    // READ - Par ID
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }
    
    // READ - Recherche par nom
    public List<Produit> rechercherParNom(String nom) {
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }
    
    // UPDATE - Modifier un produit
    public Produit modifierProduit(Long id, Produit produitDetails) {
        Optional<Produit> optionalProduit = produitRepository.findById(id);
        
        if (optionalProduit.isPresent()) {
            Produit produit = optionalProduit.get();
            produit.setNom(produitDetails.getNom());
            produit.setPrix(produitDetails.getPrix());
            produit.setQuantite(produitDetails.getQuantite());
            produit.setDescription(produitDetails.getDescription());
            return produitRepository.save(produit);
        } else {
            throw new RuntimeException("Produit non trouvé avec l'ID : " + id);
        }
    }
    
    // DELETE - Supprimer un produit
    public void supprimerProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID : " + id);
        }
        produitRepository.deleteById(id);
    }
    
    // Compter les produits
    public long compterProduits() {
        return produitRepository.count();
    }
}