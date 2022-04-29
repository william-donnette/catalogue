package controllers;

import DAO.I_DAO;
import metiers.I_Catalogue;
import metiers.I_Produit;
import metiers.Produit;

public class ProduitController {
    private I_Catalogue catalogue;
    private I_DAO dao;

    public ProduitController(I_Catalogue catalogue, I_DAO dao) {
        this.catalogue = catalogue;
        this.dao = dao;
    }

    public boolean creer(String nom, double prix, int qte) {
        I_Produit p = new Produit(nom, prix, qte);
        if(!catalogue.addProduit(p))
            return false;
        return dao.create(p) != 0;
    }

    public boolean supprimer(String nom) {
        return catalogue.removeProduit(nom);
    }

    public String[] getNomProduits(){
        return catalogue.getNomProduits();
    }
}
