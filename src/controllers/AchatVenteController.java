package controllers;

import metiers.I_Catalogue;
import DAO.I_DAO;

import java.sql.SQLException;


public class AchatVenteController {
    private I_Catalogue catalogue;
    private I_DAO<?> dao;

    public AchatVenteController(I_Catalogue catalogue, I_DAO<?> dao) {
        this.catalogue = catalogue;
        this.dao = dao;
    }

    public boolean acheter(String nom, int qte){
        return catalogue.acheterStock(nom, qte);


    }

    public boolean vendre(String nom, int qte){
        return catalogue.vendreStock(nom, qte);
    }
}
