package DAO;

import java.sql.SQLException;
import java.util.List;


public interface I_DAO<T> {
    int create(T object);
    T read(String nom);
    boolean update(T object);
    boolean delete(T object);
    List<T> findAll();
}
