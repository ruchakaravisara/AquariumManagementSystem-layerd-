package lk.ijse.aquarium.bo;

import lk.ijse.aquarium.bo.impl.*;
import lk.ijse.aquarium.dao.custom.impl.CustomerPaymentDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();

        }
        return boFactory;
    }
    public enum BOTypes{
        CUSTOMER,CUSTOMERPAYMENT,DILIVARY,EMPLOYEE,ITEM,ORDERDETAIL,ORDERS,QUERY,SUPPLIER,SUPPLIERITEMS,SUPPLIERPAYMENT,VEHICAL

    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new ManageCustomerBOImpl();
            case CUSTOMERPAYMENT:
                return new ManageCustomerPaymentBOBOImpl();
            case DILIVARY:
                return new ManageDilivaryBOImpl();
            case EMPLOYEE:
                return new ManageEmployeeBOImpl();
            case ITEM:
                return (SuperBO) new ManageItemBOImpl();
            case ORDERDETAIL:
                return (SuperBO) new placeOrderBOImpl();
            case ORDERS:
                return new ManageOrdersBOImpl();
            case SUPPLIER:
                return new ManageSupplierBOImpl();
            case SUPPLIERITEMS:
                return (SuperBO) new SupplierItemBOImpl();
            case SUPPLIERPAYMENT:
                return new ManageSupplierPaymentBOImpl();
            case VEHICAL:
                return new ManageVehicalBOImpl();
            default:
                return null;


        }
    }
}
