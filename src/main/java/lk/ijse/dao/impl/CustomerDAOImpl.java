package lk.ijse.dao.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
        return null;

    }

    @Override
    public Customer search(String id) {
      return null;

    }

    @Override
    public String currentid() {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
      NativeQuery query=session.createNativeQuery("SELECT emp_id FROM employee ORDER BY emp_id DESC LIMIT 1");
    /*  Object result=query.uniqueResult();
     String ids=result.toString();*/
        String ids = (String) query.getSingleResult();
     transaction.commit();
     session.close();
     return ids;
    }

    @Override
    public List<String> getid() {
        Session session=FactoryConfiguration.getInstance().getSession();
        Query<String> query=session.createNativeQuery("select id from Customer",String.class);
        List<String> clist=query.list();
        session.close();
        return clist;
    }

    @Override
    public String getname(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<String> query = session.createQuery("select name from Customer where id =:id ").setParameter("id",id);
        String name = query.getSingleResult();
        session.close();

        return name;
    }

    @Override
    public List<Customer> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Query<Customer> query = session.createQuery("from Customer ");
        List<Customer> clist = query.list();

        return clist;
    }

    @Override
    public Customer searchcus(String id) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customerEntity = session.get(Customer.class, id);

        transaction.commit();
        session.close();
        System.out.println(customerEntity.getId());

        return customerEntity;
    }
}
