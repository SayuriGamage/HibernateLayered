package lk.ijse.bo.impl;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.bo.CustomerBO;
import lk.ijse.dao.CustomerDAO;
import lk.ijse.dao.impl.DAOFactory;
import lk.ijse.dao.impl.DAOTypes;
import lk.ijse.entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl  implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Customer);
    @Override
    public boolean saveEmployee(CustomerDTO customerDTO) {
        return customerDAO.save(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        return customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDTO> loadallCustomer() {
                ArrayList<Customer> customers=   customerDAO.gettall();
                ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
          for(Customer customer:customers){
          CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
          customerDTOS.add(customerDTO);
          }
      return customerDTOS;
    }




}
