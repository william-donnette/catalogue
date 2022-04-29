package Factory;

import DAO.I_DAO;
import metiers.I_Catalogue;
import metiers.I_Produit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOAbstracteFactory {
    public static DAOAbstracteFactory instance;
    private static Connection cn;
    private static String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private static String login = "donnettew";
    private static String mdp = "081221408JH";

    public synchronized static DAOAbstracteFactory getInstance(){
        if(instance == null){
            instance = new DAOOracleFactory();
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn = DriverManager.getConnection(url, login, mdp);
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static Connection getConnection() {
        return cn;
    }

    public abstract I_DAO<I_Produit> createDAOProduit();
    public abstract I_DAO<I_Catalogue> createDAOCatalogue();

}
