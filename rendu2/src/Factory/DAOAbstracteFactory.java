package Factory;

import DAO.I_DAO;
import metiers.I_Produit;

public abstract class DAOAbstracteFactory {
    public static DAOAbstracteFactory instance;

    public synchronized static DAOAbstracteFactory getInstance(){
        if(instance == null)
            instance = new DAOOracleFactory();
        return instance;
    }

    public abstract I_DAO<I_Produit> createDAOProduit();

}
