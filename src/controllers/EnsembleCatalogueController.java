package controllers;

import metiers.EnsembleCatalogue;
import view.FenetrePrincipale;

public class EnsembleCatalogueController {
    private EnsembleCatalogue listeCatalogues;

    public EnsembleCatalogueController(){
        listeCatalogues = new EnsembleCatalogue();
    }

    public String[] getListeNomCatalogues() {
        return listeCatalogues.getListeNom();
    }

    public String[] getListeCatalogueAvecNbrProduits() {
        return listeCatalogues.getListeNomAvecProduits();
    }

    public boolean addCatalogue(String nom) {
        return listeCatalogues.addCatalogue(nom);
    }

    public boolean removeCatalogue(String nom) {
        return listeCatalogues.removeCatalogue(nom);
    }

    public void selectCatalogue(String nom) {
        if (listeCatalogues.existe(nom) != null){
            new FenetrePrincipale(nom);
        }
    }

    public int getSize() {
        return listeCatalogues.getSize();
    }
}
