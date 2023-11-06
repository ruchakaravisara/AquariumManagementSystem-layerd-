package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageCustomerPaymentBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.CustomerPaymentDAO;
import lk.ijse.aquarium.dto.CustomerDTO;
import lk.ijse.aquarium.dto.CustomerPaymentDTO;
import lk.ijse.aquarium.entity.Customer;
import lk.ijse.aquarium.entity.CustomerPayment;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerPaymentBOBOImpl implements ManageCustomerPaymentBO {
    CustomerPaymentDAO customerPaymentCrudDAO= (CustomerPaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERPAYMENT);

    public boolean saveAllCustomerspay(CustomerPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return customerPaymentCrudDAO.save(dto);
    }
    public boolean updateAllCustomersPay(CustomerPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return customerPaymentCrudDAO.update(dto);
    }
    public CustomerPaymentDTO searchAllCustomersPay(String id) throws SQLException, ClassNotFoundException {
         return customerPaymentCrudDAO.search(id);

    }
    public boolean deleteAllCustomersPay(String id) throws SQLException, ClassNotFoundException {
        return customerPaymentCrudDAO.Delete(id);
    }
    public ArrayList<CustomerPaymentDTO> loadAllCusttomersPay() throws SQLException, ClassNotFoundException {
      return customerPaymentCrudDAO.getData();

        //  return customerPaymentCrudDAO.getData();
    }

}
