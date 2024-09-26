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
import lk.ijse.DTO.ItemDTO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.bo.ItemBO;
import lk.ijse.entity.Item;
import lk.ijse.entity.tm.CustomerTm;
import lk.ijse.entity.tm.ItemTm;

import java.util.ArrayList;

public class ItemController {

    public TextField pricetext;
    public TextField codetext;
    public TextField descriptiontext;
    public TextField qtytext;
    public TableView<ItemTm> tblitem;
    public TableColumn <String,ItemTm> colcode;
    public TableColumn <String,ItemTm> coldescription;
    public TableColumn <String,ItemTm> colprice;
    public TableColumn <String,ItemTm> colqty;


 ItemBO itemBO= (ItemBO) BOFactory.getBoFactory().getBO(BOTypes.Itemsbo);


   public void initialize(){
       setCellValueFactory();
       loadAllItems();
   }

    private void setCellValueFactory() {
       colcode.setCellValueFactory(new PropertyValueFactory<>("code"));
       coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
       colqty.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
       colprice.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        ArrayList<ItemDTO> itemDTOS=itemBO.getallItems();
        for(ItemDTO itemDTO:itemDTOS){
            ItemTm itemTm=new ItemTm(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQty());
           obList.add(itemTm);
        }
      tblitem.setItems(obList);
    }

    public void saveOnAction(ActionEvent actionEvent) {
      String code=codetext.getText();
      String description=descriptiontext.getText();
      String price=pricetext.getText();
      String qty=qtytext.getText();


        boolean issave=itemBO.saveItem(new ItemDTO(code,description,price,qty));
        try {


            if (issave) {
                new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
            }
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,"item is not saved").show();
        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
     String code=codetext.getText();

     boolean isdelete=itemBO.deleteItem(code);

     try {

         if (isdelete) {
             new Alert(Alert.AlertType.INFORMATION, "item deleted").show();
         }
     }catch (Exception e){
         new Alert(Alert.AlertType.ERROR,"item is not deleted");
     }
    }


    public void UpdateOnAction(ActionEvent actionEvent) {
       String code=codetext.getText();
       String description=descriptiontext.getText();
       String price=pricetext.getText();
       String qty=qtytext.getText();

       boolean isUpdate=itemBO.updateItem(new ItemDTO(code,description,price,qty));


       try {


           if (isUpdate) {
               new Alert(Alert.AlertType.INFORMATION, "item updated").show();
           }
       }catch (Exception e){
           new Alert(Alert.AlertType.ERROR,"item is not updated");
       }
    }

    public void searchOnAction(ActionEvent actionEvent) {
       String code=codetext.getText();
       ArrayList<ItemDTO> itemDTOS=itemBO.searchItem(code);
     for (ItemDTO item:itemDTOS){
         codetext.setText(item.getCode());
         descriptiontext.setText(item.getDescription());
         qtytext.setText(item.getUnitPrice());
         pricetext.setText(item.getQty());
     }
    }
}
