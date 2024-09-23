package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    public TextField idtext;
    public TextField nametext;
    public TextField addresstext;
    public TableView<CustomerTm> tblcustomer;
    public TableColumn<CustomerTm, String> colid;
    public TableColumn<CustomerTm, String> colname;
    public TableColumn<CustomerTm, String> coladdress;



    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOTypes.Customerbo);



    public void initialize(){
        setCellValueFactory();
        loadallCustomer();
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
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        ArrayList<CustomerDTO> customers=customerBO.loadallCustomer();
        System.out.println("Customers loaded: " + customers);
        for(CustomerDTO customer:customers){

        CustomerTm customerTm=new CustomerTm(customer.getId(),customer.getName(),customer.getAddress());
        obList.add(customerTm);
        }
        tblcustomer.setItems(obList);


    }


    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }


}



