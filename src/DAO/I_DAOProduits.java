package DAO;

import java.util.List;

public interface I_DAOProduits<T> extends I_DAO<T> {
    List<T> findAllByCatalogue(int idCatalogue);
}
