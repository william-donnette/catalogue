package Factory;

import DAO.I_DAO;
import DAO.AdaptateurProduitXMLDAO;
import DAO.I_DAOProduits;
import metiers.I_Catalogue;
import metiers.I_Produit;



public class DAOXMLFactory extends DAOAbstracteFactory{
    @Override
    public I_DAOProduits<I_Produit> createDAOProduit() {
        return new AdaptateurProduitXMLDAO();
    }

    @Override
    public I_DAO<I_Catalogue> createDAOCatalogue() {
        return null;
    }
}
