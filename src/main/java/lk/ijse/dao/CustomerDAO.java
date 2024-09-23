package lk.ijse.dao;

import lk.ijse.entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {



    boolean save(Customer customer);

    boolean update(Customer customer);

    boolean delete(String id);

    ArrayList<Customer> gettall();

    Customer search(String id);

}
