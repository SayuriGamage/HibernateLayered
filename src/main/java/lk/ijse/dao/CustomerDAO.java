package lk.ijse.dao;

import lk.ijse.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {



    boolean save(Customer customer);

    boolean update(Customer customer);

    boolean delete(String id);

    ArrayList<Customer> gettall();

    Customer search(String id);

    String currentid();

    List<String> getid();

    String getname(String id);

    List<Customer> getAll();

    Customer searchcus(String id);

}
