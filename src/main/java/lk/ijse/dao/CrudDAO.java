package lk.ijse.dao;

import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

    boolean save(T dto) throws SQLException;
    boolean update(T dto);

    boolean delete(String id);

     ArrayList<T> gettall();
}
