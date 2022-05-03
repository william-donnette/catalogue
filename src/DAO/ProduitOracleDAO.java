package DAO;

import Factory.DAOAbstracteFactory;
import Factory.DAOOracleFactory;
import metiers.I_Produit;
import metiers.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitOracleDAO implements I_DAOProduits<I_Produit> {

    @Override
    public int create(I_Produit object) {
        CallableStatement cst = null;
        try{
            cst = DAOOracleFactory.getConnection().prepareCall("{? = call creerProduit(?, ?, ?, ?)}");
            cst.setString(2, object.getNom());
            cst.setInt(3, object.getQuantite());
            cst.setDouble(4, object.getPrixUnitaireHT());
            cst.setInt(5, object.getIdCatalogue());
            cst.registerOutParameter(1, Types.NUMERIC);
            cst.execute();
            return cst.getInt(1);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public I_Produit read(String nom) {
        return null;
    }

    @Override
    public boolean update(I_Produit object) {
        boolean update = false;
        try{
            PreparedStatement ps = DAOOracleFactory.getConnection().prepareStatement("UPDATE Produits SET quantiteStock=?, prixUnitaireHT=? WHERE nom=? AND idCatalogue=?");
            ps.setInt(1, object.getQuantite());
            ps.setDouble(2, object.getPrixUnitaireHT());
            ps.setString(3, object.getNom());
            ps.setInt(4, object.getIdCatalogue());
            update = !ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }

        return update;
    }

    @Override
    public boolean delete(I_Produit object) {
        boolean delete = false;
        try{
            PreparedStatement ps = DAOOracleFactory.getConnection().prepareStatement("DELETE FROM Produits WHERE nom=? AND idCatalogue=?");
            ps.setString(1, object.getNom());
            ps.setInt(2, object.getIdCatalogue());
            delete = !ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return delete;
    }

    @Override
    public List<I_Produit> findAll(){
        List<I_Produit> l = new ArrayList<>();
        try{
            Statement st = DAOOracleFactory.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM Produits ORDER BY nom ASC");

            while(rs.next()){
                l.add(new Produit(rs.getString("nom"), rs.getDouble("prixUnitaireHT"), rs.getInt("quantiteStock")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return l;
    }

    @Override
    public List<I_Produit> findAllByCatalogue(int idCatalogue){
        List<I_Produit> l = new ArrayList<>();
        try{
            PreparedStatement ps = DAOOracleFactory.getConnection().prepareStatement("SELECT * FROM Produits WHERE idCatalogue=? ORDER BY nom ASC", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1,idCatalogue);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                l.add(new Produit(rs.getString("nom"), rs.getDouble("prixUnitaireHT"), rs.getInt("quantiteStock"), rs.getInt("idCatalogue")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return l;
    }
}
