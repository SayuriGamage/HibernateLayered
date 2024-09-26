package lk.ijse.dao;

import lk.ijse.entity.Item;

import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends SuperDAO{
    boolean save(Item item);

    boolean delete(String code);

    boolean update(Item item);

    ArrayList<Item> gettall();

    ArrayList<Item> search(String code);

    List<String> getids();

    Item getdata(String value);

}
