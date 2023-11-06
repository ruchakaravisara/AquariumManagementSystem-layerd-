package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageCustomerBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.CustomerDAO;
import lk.ijse.aquarium.dto.CustomerDTO;
import lk.ijse.aquarium.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerBOImpl implements ManageCustomerBO {
     CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean saveAllCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
      return customerDAO.save(dto);
    }
    public boolean updateAllCustomers(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(dto);
    }
    public CustomerDTO searchAllCustomers(String id) throws SQLException, ClassNotFoundException {
        return   customerDAO.search(id);

    }
    public boolean deleteAllCustomers(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.Delete(id);
    }
    public ArrayList<CustomerDTO> loadAllCusttomers() throws SQLException, ClassNotFoundException {



        return customerDAO.getData();
    }
    public String getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getID();
    }
}
