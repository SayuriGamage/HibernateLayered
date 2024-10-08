package lk.ijse.bo;

import lk.ijse.bo.impl.CustomerBOImpl;
import lk.ijse.bo.impl.ItemBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }


    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Customerbo :
                return  new CustomerBOImpl();

            case Itemsbo:
                return new ItemBOImpl();
        }
        return null;
    }
}
