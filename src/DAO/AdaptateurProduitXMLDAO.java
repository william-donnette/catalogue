package DAO;

import metiers.I_Produit;
import java.util.List;

public class AdaptateurProduitXMLDAO implements I_DAOProduits<I_Produit> {

    private ProduitDAO_XML px;

    public AdaptateurProduitXMLDAO(){
        px = new ProduitDAO_XML();
    }

    @Override
    public int create(I_Produit object) {
        return px.creer(object) ? 1 : 0;
    }

    @Override
    public I_Produit read(String nom) {
        return px.lire(nom);
    }

    @Override
    public boolean update(I_Produit object) {
        return px.maj(object);
    }

    @Override
    public boolean delete(I_Produit object) {
        return px.supprimer(object);
    }

    @Override
    public List<I_Produit> findAll() {
        return px.lireTous();
    }@Override
    public List<I_Produit> findAllByCatalogue(int idCatalogue) {
        return null;
    }

    }
