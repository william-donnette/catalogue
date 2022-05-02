package Factory;

import DAO.CatalogueOracleDAO;
import DAO.I_DAO;
import DAO.I_DAOProduits;
import DAO.ProduitOracleDAO;
import metiers.I_Catalogue;
import metiers.I_Produit;

public class DAOOracleFactory extends DAOAbstracteFactory {
    @Override
    public I_DAOProduits<I_Produit> createDAOProduit() {
        return new ProduitOracleDAO();
    }

    @Override
    public I_DAO<I_Catalogue> createDAOCatalogue() {
        return new CatalogueOracleDAO();
    }
}
