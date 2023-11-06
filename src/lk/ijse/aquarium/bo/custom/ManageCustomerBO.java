package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageCustomerBO extends SuperBO {
    public boolean saveAllCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException ;
    public CustomerDTO searchAllCustomers(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllCustomers(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<CustomerDTO> loadAllCusttomers() throws SQLException, ClassNotFoundException ;
    public String getCustomerId() throws SQLException, ClassNotFoundException ;
}
