package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.CustomerPaymentDAO;
import lk.ijse.aquarium.dto.CustomerPaymentDTO;
import lk.ijse.aquarium.entity.CustomerPayment;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerPaymentDAOImpl implements CustomerPaymentDAO {
    public  boolean save(CustomerPaymentDTO customerPaymentDTO) throws SQLException, ClassNotFoundException {

       Boolean saveCustomerPayment = CrudUtil.execute("INSERT INTO Customerpayment VALUES (?, ?, ?)", customerPaymentDTO.getId(), customerPaymentDTO.getValue(), customerPaymentDTO.getcId());
        return saveCustomerPayment;

    }
    public  boolean update(CustomerPaymentDTO customerPaymentDTO) throws SQLException, ClassNotFoundException {
        Boolean updateCustomerPay = CrudUtil.execute(" UPDATE customerpayment SET value = ? , cid = ?  WHERE CPid = ? ", customerPaymentDTO.getValue(), customerPaymentDTO.getcId());
        return updateCustomerPay;
    }

    public CustomerPaymentDTO search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Customerpayment WHERE CPid = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new CustomerPaymentDTO(
                    result.getString("id"),
                    result.getDouble("value"),
                    result.getString("cId")
            );
        }
        return null;
    }

    public  boolean Delete( String id) throws SQLException, ClassNotFoundException {
      Boolean isdeleteCustomerPayment = CrudUtil.execute(" DELETE FROM customerpayment  WHERE CPid = ? " ,id);
        return isdeleteCustomerPayment;
    }

    @Override
    public String getID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  ArrayList<CustomerPaymentDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerPaymentDTO> customerpayment = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from customerpayment");
        while (resultSet.next()){

            customerpayment.add(new CustomerPaymentDTO(resultSet.getString("id"),
                    resultSet.getDouble("value"),
                    resultSet.getString("cId")));
        }
        return customerpayment;
    }

    @Override
    public void play() {

    }
}
