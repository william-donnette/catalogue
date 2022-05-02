package controllers;

import DAO.I_DAO;
import metiers.I_Catalogue;
import metiers.I_Produit;
import metiers.Produit;

public class ProduitController {
    private I_Catalogue catalogue;

    public ProduitController(I_Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public boolean creer(String nom, double prix, int qte) {
        return catalogue.addProduit(nom, prix, qte);
    }

    public boolean supprimer(String nom) {
        return catalogue.removeProduit(nom);
    }

    public String[] getNomProduits(){
        return catalogue.getNomProduits();
    }
}
