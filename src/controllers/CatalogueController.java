package controllers;

import metiers.I_Catalogue;
import metiers.EnsembleCatalogue;

public class CatalogueController {

    private AchatVenteController achatVenteController;
    private EtatStockController etatStockController;
    private ProduitController produitController;


    public CatalogueController(String nomCatalogue){
            EnsembleCatalogue catalogues = new EnsembleCatalogue();
            I_Catalogue catalogue = catalogues.existe(nomCatalogue);
            catalogue.getProduits();
            achatVenteController = new AchatVenteController(catalogue);
            etatStockController = new EtatStockController(catalogue);
            produitController = new ProduitController(catalogue);
    }

    public String lister(){
        return etatStockController.lister();
    }

    public boolean creerProduit(String nom, String prix, String qte){
        try{
            return produitController.creer(nom, Double.parseDouble(prix), Integer.parseInt(qte));
        }catch(NumberFormatException ignored){
        }
        return false;
    }

    public boolean supprimerProduit(String nom){
        return produitController.supprimer(nom);
    }

    public boolean acheterProduit(String nom, String qte){
        try {
            return achatVenteController.acheter(nom, Integer.parseInt(qte));
        }catch(NumberFormatException ignored){
        }
        return false;
    }

    public boolean vendreProduit(String nom, String qte){
        try {
            return achatVenteController.vendre(nom, Integer.parseInt(qte));
        }catch(NumberFormatException ignored){
        }
        return false;
    }

    public String[] listeNomProduits(){
        return produitController.getNomProduits();
    }
}
