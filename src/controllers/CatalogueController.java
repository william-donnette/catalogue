package controllers;

import DAO.I_DAO;
import DAO.ProduitOracleDAO;
import Factory.DAOAbstracteFactory;
import metiers.Catalogue;
import metiers.I_Catalogue;
import metiers.I_Produit;


import java.sql.SQLException;

public class CatalogueController {

    private AchatVenteController achatVenteController;
    private EtatStockController etatStockController;
    private ProduitController produitController;
    private I_DAO<I_Produit> daoProd;
    private I_DAO<I_Catalogue> daoCat;


    public CatalogueController(String nomCatalogue){
            daoProd = DAOAbstracteFactory.getInstance().createDAOProduit();
            daoCat = DAOAbstracteFactory.getInstance().createDAOCatalogue();
            I_Catalogue catalogue = daoCat.read(nomCatalogue);
            catalogue.setDAO(daoProd);
            catalogue.addProduits(daoProd.findAll());
            achatVenteController = new AchatVenteController(catalogue, daoProd);
            etatStockController = new EtatStockController(catalogue, daoProd);
            produitController = new ProduitController(catalogue, daoProd);
    }

    public String lister(){
        return etatStockController.lister();
    }

    public boolean creerProduit(String nom, String prix, String qte){
        try{
            return produitController.creer(nom, Double.parseDouble(prix), Integer.parseInt(qte));
        }catch(NumberFormatException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean supprimerProduit(String nom){
        return produitController.supprimer(nom);
    }

    public boolean acheterProduit(String nom, String qte){
        try {
            return achatVenteController.acheter(nom, Integer.parseInt(qte));
        }catch(NumberFormatException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean vendreProduit(String nom, String qte){
        try {
            return achatVenteController.vendre(nom, Integer.parseInt(qte));
        }catch(NumberFormatException e){
            System.out.println(e);
        }
        return false;
    }

    public String[] listeNomProduits(){
        return produitController.getNomProduits();
    }
}
