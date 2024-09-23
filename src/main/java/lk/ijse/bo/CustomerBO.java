package lk.ijse.bo;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.entity.Customer;

import java.util.ArrayList;

public interface CustomerBO  extends SuperBO{


    boolean saveEmployee(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String id);

    ArrayList<CustomerDTO> loadallCustomer();


}
