package DAO;

import metiers.Catalogue;
import metiers.I_Catalogue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CatalogueOracleDAO implements I_DAO<I_Catalogue> {

    @Override
    public int create(I_Catalogue object) {
        return 0;
    }

    @Override
    public I_Catalogue read(String nom) {
        return new Catalogue();
    }

    @Override
    public boolean update(I_Catalogue object) {
        return false;
    }

    @Override
    public boolean delete(I_Catalogue object) {
        return false;
    }

    @Override
    public List<I_Catalogue> findAll() {
        return null;
    }
}
