package Factory;

import DAO.CatalogueOracleDAO;
import DAO.I_DAO;
import DAO.I_DAOProduits;
import DAO.ProduitOracleDAO;
import metiers.I_Catalogue;
import metiers.I_Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOOracleFactory extends DAOAbstracteFactory {
    private static Connection cn;
    private static String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private static String login = "donnettew";
    private static String mdp = "081221408JH";

    public DAOOracleFactory() {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(url, login, mdp);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return cn;
    }

    @Override
    public I_DAOProduits<I_Produit> createDAOProduit() {
        return new ProduitOracleDAO();
    }

    @Override
    public I_DAO<I_Catalogue> createDAOCatalogue() {
        return new CatalogueOracleDAO();
    }
}
