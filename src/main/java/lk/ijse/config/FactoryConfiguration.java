package lk.ijse.config;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration config = new Configuration().configure().addAnnotatedClass(Customer.class).addAnnotatedClass(Item.class).addAnnotatedClass(Orders.class);
        sessionFactory = config.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null? new FactoryConfiguration(): factoryConfiguration);
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
