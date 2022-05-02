package Factory;

import DAO.I_DAO;
import DAO.ProduitOracleDAO;
import metiers.I_Produit;

public class DAOOracleFactory extends DAOAbstracteFactory {
    @Override
    public I_DAO<I_Produit> createDAOProduit() {

        return new ProduitOracleDAO("jdbc:oracle:thin:@162.38.222.149:1521:iut", "donnettew", "081221408JH");

    }
}
