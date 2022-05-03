package metiers;

import DAO.I_DAO;
import Factory.DAOAbstracteFactory;
import view.FenetrePrincipale;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnsembleCatalogue {

    private I_DAO dao;
    private List<I_Catalogue> catalogues;

    public EnsembleCatalogue() {
        dao = DAOAbstracteFactory.getInstance().createDAOCatalogue();;
        catalogues = getAll();
        for (I_Catalogue catalogue : catalogues){
            catalogue.getProduits();
        }
    }

    public List<I_Catalogue> getAll(){
        return dao.findAll();
    }

    public String[] getListeNom(){
        List<String> listeNomCatalogue = new ArrayList<>();
        for (I_Catalogue catalogue: catalogues) {
            listeNomCatalogue.add(catalogue.getNom());
        }
        String [] listeNoms = new String [listeNomCatalogue.size()];
        listeNomCatalogue.toArray(listeNoms);
        return listeNoms;
    }

    public boolean addCatalogue(String nomCatalogue) {
        return addCatalogue(new Catalogue(nomCatalogue));
    }

    public boolean addCatalogue(I_Catalogue catalogue) {
        if(existe(catalogue) == null && catalogue.isValidNom()){
            int idCatalogue = dao.create(catalogue);
            if ( idCatalogue != -1) {
                catalogue.setId(idCatalogue);
                catalogues.add(catalogue);
                return true;
            }
        }
        return false;
    }

    public boolean removeCatalogue(String nom) {
        return removeCatalogue(existe(nom));
    }



    public boolean removeCatalogue(I_Catalogue catalogue) {
        if(existe(catalogue) != null && dao.delete(catalogue)){
            catalogues.remove(catalogue);
            return true;
        }
        return false;
    }

    public I_Catalogue existe(I_Catalogue catalogue){
        return existe(catalogue.getNom());
    }

    public I_Catalogue existe(String nom) {
        I_Catalogue c = null;
        try {
            for (I_Catalogue catalogue : catalogues) {
                if (catalogue.getNom().equals(nom.trim().replaceAll("\t", " ").replaceAll(" +", " "))) {
                    c = catalogue;
                    break;
                }
            }
        }catch(NullPointerException e){
        }
        return c;
    }

    public String[] getListeNomAvecProduits() {
        List<String> listeNomCatalogue = new ArrayList<>();
        for (I_Catalogue catalogue: catalogues) {
            listeNomCatalogue.add(catalogue.getNom() + " : " + catalogue.getNbrProduits() + " produits");
        }
        String [] listeNoms = new String [listeNomCatalogue.size()];
        listeNomCatalogue.toArray(listeNoms);
        return listeNoms;
    }

    public int getSize() {
        return catalogues.size();
    }
}
