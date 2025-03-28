package com.example.stock.storestock;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class StockService {

    private final Map<String, Integer> stock = new HashMap<>();

    // Méthode pour obtenir l'état actuel du stock
    public Map<String, Integer> getStock() {
        return stock;
    }

    // Méthode pour mettre à jour le stock avec un article et une quantité
    public void updateStock(String nomArticle, int quantite) {
        // Mise à jour du stock
        stock.put(nomArticle, stock.getOrDefault(nomArticle, 0) - quantite);
    }
    
 // Méthode pour ajouter au stock avec un article et une quantité
    public void addStock(String nomArticle, int quantite) {
        // Mise à jour du stock
        stock.put(nomArticle, stock.getOrDefault(nomArticle, 0) + quantite);
    }

    // Méthode pour réapprovisionner le stock avec des articles fictifs
    public void reapprovisionner() {
    	addStock("Thé", 10);
    	addStock("Yaourt", 5);
    	addStock("Pain", 4);
    }
}