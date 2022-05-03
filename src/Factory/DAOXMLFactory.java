package Factory;

import DAO.*;
import metiers.I_Catalogue;
import metiers.I_Produit;

import java.sql.Connection;


public class DAOXMLFactory extends DAOAbstracteFactory{

    @Override
    public I_DAOProduits<I_Produit> createDAOProduit() {
        return new ProduitDAOXMLv2();
    }

    @Override
    public I_DAO<I_Catalogue> createDAOCatalogue() {
        return new CatalogueXMLDAO();
    }
}
