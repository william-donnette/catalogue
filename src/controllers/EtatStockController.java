package controllers;
import DAO.I_DAO;
import metiers.I_Catalogue;

public class EtatStockController {
    private I_Catalogue catalogue;
    private I_DAO<?> dao;

    public EtatStockController(I_Catalogue catalogue, I_DAO<?> dao) {
        this.catalogue = catalogue;
        this.dao = dao;
    }

    public String lister(){
        return catalogue.toString();
    }
}
