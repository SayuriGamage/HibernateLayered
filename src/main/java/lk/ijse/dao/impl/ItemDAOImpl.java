package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.ItemDAO;
import lk.ijse.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean save(Item item) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        Object object=session.save(item);
        if(object!=null){
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String code) {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        NativeQuery query=session.createNativeQuery("delete from item where code=?1");
        query.setParameter(1,code);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item item) {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
         session.update(item);
         transaction.commit();
         session.close();
         return true;
    }

    @Override
    public ArrayList<Item> gettall() {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        ArrayList<Item> items=new ArrayList<>();
      NativeQuery query=session.createNativeQuery("select * from item");
      List<Object[]> result=query.list();
      for (Object[] object:result){
          Item item=new Item(object[0].toString(),object[1].toString(),object[2].toString(),object[3].toString());
          items.add(item);
      }
      transaction.commit();
      session.close();
      return items;
    }

    @Override
    public ArrayList<Item> search(String code) {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        ArrayList<Item> it=new ArrayList<>();
        NativeQuery query=session.createNativeQuery("select * from item where code=?1");
        query.setParameter(1,code);
        List<Object[]> result=query.list();
        for (Object[] objects:result){
            Item item=new Item(objects[0].toString(),objects[1].toString(),objects[2].toString(),objects[3].toString());
            it.add(item);
        }
        transaction.commit();
        session.close();
        return it;
    }

    @Override
    public List<String> getids() {
        Session session=FactoryConfiguration.getInstance().getSession();
        Query<String> query=session.createNativeQuery("select code from Item",String.class);
        List<String> cllist=query.list();
        session.close();
        return cllist;
    }

    @Override
    public Item getdata(String value) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Item> query = session.createQuery("from Item where code =:id ").setParameter("id",value);
        Item item = query.getSingleResult();
        session.close();

        return item;
    }
}
