package DAO;

import Factory.DAOAbstracteFactory;
import Factory.DAOOracleFactory;
import metiers.Catalogue;
import metiers.I_Catalogue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogueOracleDAO implements I_DAO<I_Catalogue> {

    @Override
    public int create(I_Catalogue object) {
        CallableStatement cst = null;
        try{
            cst = DAOOracleFactory.getConnection().prepareCall("{? = call creerCatalogue(?)}");
            cst.setString(2, object.getNom());
            cst.registerOutParameter(1, Types.NUMERIC);
            cst.execute();
            return cst.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public I_Catalogue read(String nom) {
        PreparedStatement pst = null;
        try{
            pst = DAOOracleFactory.getConnection().prepareCall("SELECT * FROM catalogues WHERE nom = ?");
            pst.setString(1,nom);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return new Catalogue(rs.getInt("idCatalogue"), rs.getString("nom"));
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(I_Catalogue object) {
        return false;
    }

    @Override
    public boolean delete(I_Catalogue object) {
        boolean delete = false;
        try{
            PreparedStatement ps = DAOOracleFactory.getConnection().prepareStatement("DELETE FROM catalogues WHERE nom = ?");
            ps.setString(1, object.getNom());
            delete = !ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return delete;
    }

    @Override
    public List<I_Catalogue> findAll() {
        List<I_Catalogue> l = new ArrayList<>();
        try{
            Statement st = DAOOracleFactory.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM catalogues ORDER BY nom ASC");

            while(rs.next()){
                l.add(new Catalogue(rs.getInt("idCatalogue"), rs.getString("nom")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return l;
    }
}
