package lk.ijse.aquarium.dao;

import lk.ijse.aquarium.dao.custom.impl.*;

public class DAOFactory {
    private  static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            daoFactory= new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,CUSTOMERPAYMENT,DILIVARY,EMPLOYEE,ITEM,ORDERDETAIL,ORDERS,QUERY,SUPPLIER,SUPPLIERITEMS,SUPPLIERPAYMENT,VEHICAL
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return  new CustomerDAOImpl();
            case CUSTOMERPAYMENT:
                return new CustomerPaymentDAOImpl();
            case DILIVARY:
                return new DilivaryDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case ORDERS:
                return new OrdersDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIERITEMS:
                return new SupplierItemDAOImpl();
            case SUPPLIERPAYMENT:
                return new SupplierPaymentDAOImpl();
            case VEHICAL:
                return new VehicalDAOImpl();
            default:
                return null;

        }

    }
}
