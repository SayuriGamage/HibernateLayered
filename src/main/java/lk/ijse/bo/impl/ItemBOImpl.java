package lk.ijse.bo.impl;

import lk.ijse.DTO.CustomerDTO;
import lk.ijse.DTO.ItemDTO;
import lk.ijse.bo.ItemBO;
import lk.ijse.bo.SuperBO;
import lk.ijse.dao.ItemDAO;
import lk.ijse.dao.impl.DAOFactory;
import lk.ijse.dao.impl.DAOTypes;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {



    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.Item);
    @Override
    public boolean saveItem(ItemDTO itemDTO) {
         return itemDAO.save(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQty()));
    }

    @Override
    public boolean deleteItem(String code) {
        return itemDAO.delete(code);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        return itemDAO.update(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQty()));
    }

    @Override
    public ArrayList<ItemDTO> getallItems() {
       ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
       ArrayList<Item> items=itemDAO.gettall();
        for(Item item:items) {
            ItemDTO itemDTO=new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQty());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public ArrayList<ItemDTO> searchItem(String code) {
     ArrayList<ItemDTO> itemDTOS=new ArrayList<>();

     ArrayList<Item> items=itemDAO.search(code);
     for (Item item:items){
         ItemDTO itemDTO=new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQty());
         itemDTOS.add(itemDTO);
     }
     return itemDTOS;
    }

    @Override
    public List<String> getIds() {
        List<String> list=itemDAO.getids();
        return list;
    }

    @Override
    public ItemDTO getdata(String value) {
        Item e=itemDAO.getdata(value);
        return new ItemDTO(e.getCode(),e.getDescription(),e.getUnitPrice(),e.getQty());
    }
}
