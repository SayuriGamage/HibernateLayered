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

 /*   @Override
    public Customer search(String id) {

       Session session=FactoryConfiguration.getInstance().getSession();
       Transaction transaction=session.beginTransaction();
       NativeQuery query=session.createNativeQuery("select * from Customer where id=?1");
        query.addEntity(Customer.class);
       query.setParameter(1,id);
    Object[] objects= (Object[]) query.uniqueResult();
    Customer customer=new Customer(objects[0].toString(),objects[])
        transaction.commit();
        session.close();
      return customer;
    }*/
}
