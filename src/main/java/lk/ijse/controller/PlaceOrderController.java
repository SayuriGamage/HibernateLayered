package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.DTO.ItemDTO;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.BOTypes;
import lk.ijse.bo.CustomerBO;
import lk.ijse.bo.ItemBO;

import java.util.List;

public class PlaceOrderController {

    public TextField ordertext;
    public TextField cusnametext;
    public TextField desctext;
    public TextField pricetext;
    public TextField qtyOntext;
    public TextField qtytext;
    public ComboBox <String>itemcomtext;
    public ComboBox<String> cuscomid;
    public TableView tblorder;
    public TableColumn colorid;
    public TableColumn colorname;
    public TableColumn colordescription;
    public TableColumn colorunitprice;
    public TableColumn colorqty;

    ItemBO itemBO= (ItemBO) BOFactory.getBoFactory().getBO(BOTypes.Itemsbo);
    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOTypes.Customerbo);

    public void initialize(){
        setCustomerCmb();
        setItemcmb();
    }

    private void setItemcmb() {
        ObservableList<String> oblist= FXCollections.observableArrayList();
        List<String> ilist=itemBO.getIds();
        for(String id:ilist){
            oblist.add(id);
        }
        itemcomtext.setItems(oblist);
    }

    private void setCustomerCmb() {
        ObservableList<String> obList=FXCollections.observableArrayList();
        List<String> clist=customerBO.getcids();
        for(String party:clist){
            obList.add(party);
        }
        cuscomid.setItems(obList);
    }

    public void saveOnAction(ActionEvent actionEvent) {

    }

    public void itemOnAction(ActionEvent actionEvent) {
        System.out.println("jjj");
     String value=itemcomtext.getValue();
        System.out.println(value);
        ItemDTO i=itemBO.getdata(value);
        desctext.setText(i.getDescription());
        pricetext.setText(i.getUnitPrice());
        qtyOntext.setText(i.getQty());
    }

    public void customerOnAction(ActionEvent actionEvent) {
        String id = cuscomid.getValue();
        String nametxt = customerBO.getname(id);
        cusnametext.setText(nametxt);
    }
}
