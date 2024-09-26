package lk.ijse.bo.impl;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.bo.CustomerBO;
import lk.ijse.dao.CustomerDAO;
import lk.ijse.dao.impl.DAOFactory;
import lk.ijse.dao.impl.DAOTypes;
import lk.ijse.entity.Customer;

import java.util.ArrayList;
import java.util.List;

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


    public Customer searchCustomer(String id) {

      return customerDAO.search(id);
    }

    @Override
    public String CurrentCustomerid() {
        return customerDAO.currentid();
    }

    @Override
    public List<String> getcids() {
        List<String> list=customerDAO.getid();
        return list;
    }

    @Override
    public String getname(String id) {
        String namen = customerDAO.getname(id);
        return namen;
    }

    @Override
    public ArrayList<CustomerDTO> getAll() {
        ArrayList<CustomerDTO> cList= new ArrayList<>();
        List<Customer> en = customerDAO.getAll();

        for (Customer e: en
        ) {
            cList.add(new CustomerDTO(e.getId(),e.getName(),e.getAddress()));

        }

        return cList;
    }

    @Override
    public CustomerDTO searchbyid(String id) {
        Customer e = customerDAO.searchcus(id);
        return new CustomerDTO(e.getId(),e.getName(),e.getAddress());
    }


}
