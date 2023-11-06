package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.CustomerPaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageCustomerPaymentBO  extends SuperBO {


    public boolean saveAllCustomerspay(CustomerPaymentDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllCustomersPay(CustomerPaymentDTO dto) throws SQLException, ClassNotFoundException ;
    public CustomerPaymentDTO searchAllCustomersPay(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllCustomersPay(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<CustomerPaymentDTO> loadAllCusttomersPay() throws SQLException, ClassNotFoundException ;
}
