package lk.ijse.bo;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO{
    boolean saveItem(ItemDTO itemDTO);

    boolean deleteItem(String code);

    boolean updateItem(ItemDTO itemDTO);

    ArrayList<ItemDTO> getallItems();

    ArrayList<ItemDTO> searchItem(String code);

    List<String> getIds();

    ItemDTO getdata(String value);

}
