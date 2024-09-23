package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.save(customer);
        if (object !=null) {
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
         session.update(customer);
         transaction.commit();
         session.close();
       return true;

    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery query=session.createNativeQuery("delete from Customer where id=?1");
        query.setParameter(1,id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Customer> gettall() {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        ArrayList<Customer> cus = new ArrayList<>();
        NativeQuery query = session.createNativeQuery("select * from Customer");
        List<Object[]> objects = query.list();
        for (Object[] object : objects) {
            Customer customer = new Customer(object[0].toString(), object[1].toString(), object[2].toString());

            cus.add(customer);
        }
       transaction.commit();
        session.close();
        return cus;
    }

    @Override
    public Customer search(String id) {

       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction=session.beginTransaction();
      /* NativeQuery query=session.createNativeQuery("select * from Customer where id=?1");
        query.addEntity(Customer.class);
       query.setParameter(1,id);
       Customer customer= (Customer) query.uniqueResult();
        transaction.commit();
        session.close();
      return customer;*/
        ArrayList<Customer> cuslist=new ArrayList<>();
        NativeQuery query = session.createNativeQuery("select * from Customer where id=?1");
        query.setParameter(1,id);
        List<Object[]> objects=query.list();
        for(Object[] customer:objects){
            Customer customer1=new Customer(customer[0].toString(),customer[1].toString(),customer[2].toString());
            System.out.println(customer[0].toString()+""+customer[1].toString()+""+customer[2].toString());
            transaction.commit();
            session.close();
            return customer1;
        }
      return null;

    }
}
