package Factory;

import DAO.I_DAO;
import DAO.I_DAOProduits;
import metiers.I_Catalogue;
import metiers.I_Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOAbstracteFactory {
    public static DAOAbstracteFactory instance;


    public synchronized static DAOAbstracteFactory getInstance(){
        if(instance == null){
            instance = new DAOXMLFactory();
        }
        return instance;
    }

    public abstract I_DAOProduits<I_Produit> createDAOProduit();
    public abstract I_DAO<I_Catalogue> createDAOCatalogue();

}
