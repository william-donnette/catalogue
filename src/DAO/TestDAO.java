package DAO;

import metiers.I_Produit;

import java.util.List;

public class TestDAO implements I_DAO<I_Produit> {
    @Override
    public int create(I_Produit object) {
        return 0;
    }

    @Override
    public I_Produit read(String nom) {
        return null;
    }

    @Override
    public boolean update(I_Produit object) {
        return true;
    }

    @Override
    public boolean delete(I_Produit object) {
        return true;
    }

    @Override
    public List<I_Produit> findAll() {
        return null;
    }
}
