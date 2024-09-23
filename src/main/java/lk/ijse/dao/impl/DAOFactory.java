package lk.ijse.dao.impl;

import lk.ijse.dao.SuperDAO;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case Customer:
                return new CustomerDAOImpl();
        }
        return null;
    }


}
