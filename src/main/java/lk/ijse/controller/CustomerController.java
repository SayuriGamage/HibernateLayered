package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.DTO.CustomerDTO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.bo.CustomerBO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.tm.CustomerTm;

import java.util.ArrayList;

public class CustomerController {
    public TextField idtext;
    public TextField nametext;
    public TextField addresstext;
    public TableView<CustomerDTO> tblcustomer;
    public TableColumn<CustomerTm, String> colid;
    public TableColumn<CustomerTm, String> colname;
    public TableColumn<CustomerTm, String> coladdress;



    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOTypes.Customerbo);



    public void initialize(){
        setCellValueFactory();
        loadallCustomer();

     /*   String cusid=customerBO.CurrentCustomerid();
        String customerid=generateNextId(cusid);
         idtext.setText(customerid);*/
    }

    private String generateNextId(String cusid) {

            int currentId = Integer.parseInt(cusid); // Convert the String to an integer

            if (currentId >= 1 && currentId < 9) {
                return String.valueOf(currentId + 1); // Increment and convert back to String
            } else {
                return "1"; // Reset to "1" when currentId reaches 9
            }
    }


    public void saveOnAction(ActionEvent actionEvent) {
        String id = idtext.getText();
        String name = nametext.getText();
        String address = addresstext.getText();

        try {

            boolean isSaved = customerBO.saveEmployee(new CustomerDTO(id,name,address));

            if (isSaved) {

                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();

            }


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = idtext.getText();
        String name = nametext.getText();
        String address = addresstext.getText();

        //  Employee employee = new Employee(id, name, address, tel);

        try {

            boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(id,name,address));
            if (isUpdated) {
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = idtext.getText();

        try {

            boolean isDeleted = customerBO.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void loadallCustomer(){
      /*  ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        ArrayList<CustomerDTO> customers=customerBO.loadallCustomer();
        for(CustomerDTO customer:customers){

        CustomerTm customerTm=new CustomerTm(customer.getId(),customer.getName(),customer.getAddress());
        obList.add(customerTm);
        }
        tblcustomer.setItems(obList);*/
        ObservableList<CustomerDTO> customerlist= FXCollections.observableArrayList();
        ArrayList<CustomerDTO> clist = customerBO.getAll();
        for (CustomerDTO c: clist
        ) {
            customerlist.add(c);
        }
        System.out.println("sdf");
        System.out.println(customerlist);
        tblcustomer.setItems(customerlist);
    }


    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("name"));
        colname.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String id = idtext.getText();
        CustomerDTO customerDTO = customerBO.searchbyid(id);
        idtext.setText(customerDTO.getId());
        addresstext.setText(customerDTO.getName());
        nametext.setText(customerDTO.getAddress());


    }
}



