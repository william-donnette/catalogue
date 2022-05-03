package Factory;

import DAO.AdaptateurProduitXMLDAO;
import DAO.I_DAO;
import DAO.I_DAOProduits;
import DAO.TestDAO;
import metiers.I_Catalogue;
import metiers.I_Produit;

import java.sql.Connection;

public class DAOTestFactory extends DAOAbstracteFactory {

    @Override
    public I_DAOProduits<I_Produit> createDAOProduit() {
            return new TestDAO();
        }

        @Override
        public I_DAO<I_Catalogue> createDAOCatalogue() {
            return null;
        }
}
