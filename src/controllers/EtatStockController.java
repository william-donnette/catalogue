package controllers;
import DAO.I_DAO;
import metiers.I_Catalogue;

public class EtatStockController {
    private I_Catalogue catalogue;

    public EtatStockController(I_Catalogue catalogue) {
        this.catalogue = catalogue;
    }

    public String lister(){
        return catalogue.toString();
    }
}
