package Factory;

import DAO.I_DAO;
import DAO.AdaptateurProduitXMLDAO;
import metiers.I_Produit;



public class DAOXMLFactory extends DAOAbstracteFactory{
    @Override
    public I_DAO<I_Produit> createDAOProduit() {
        return new AdaptateurProduitXMLDAO();
    }
}
