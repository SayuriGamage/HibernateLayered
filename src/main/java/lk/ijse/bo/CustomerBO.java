package lk.ijse.bo;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerBO  extends SuperBO{


    boolean saveEmployee(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String id);

    ArrayList<CustomerDTO> loadallCustomer();

    Customer searchCustomer(String id);

    String CurrentCustomerid();

    List<String> getcids();

    String getname(String id);

    ArrayList<CustomerDTO> getAll();

    CustomerDTO searchbyid(String id);
}
