package com.example.tpmonolithique.repository;

import com.example.tpmonolithique.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    // Recherche par nom (contient)
    List<Produit> findByNomContainingIgnoreCase(String nom);
    
    // Recherche avec pagination
    Page<Produit> findByNomContainingIgnoreCase(String nom, Pageable pageable);
    
    // Produits dont le prix est dans une plage
    List<Produit> findByPrixBetween(Double minPrix, Double maxPrix);
    
    // Produits en rupture de stock
    @Query("SELECT p FROM Produit p WHERE p.quantite = 0")
    List<Produit> findProduitsEnRupture();
    
    // Produits avec stock faible
    @Query("SELECT p FROM Produit p WHERE p.quantite < :seuil")
    List<Produit> findProduitsStockFaible(@Param("seuil") Integer seuil);
    
    // Compter les produits par nom
    long countByNomContainingIgnoreCase(String nom);
}