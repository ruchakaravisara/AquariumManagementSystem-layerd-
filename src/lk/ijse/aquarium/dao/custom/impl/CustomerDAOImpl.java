package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.CustomerDAO;
import lk.ijse.aquarium.dto.CustomerDTO;
import lk.ijse.aquarium.entity.Customer;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public  boolean save(CustomerDTO customerDto) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO Customer VALUES (?, ?, ?, ?)",
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getContact());


    }


   public boolean update(CustomerDTO customerDto) throws SQLException, ClassNotFoundException {
       Boolean isUpdated = CrudUtil.execute(" UPDATE customer SET name = ? , address = ? , contact = ? WHERE Cid = ? ", customerDto.getName(), customerDto.getAddress(), customerDto.getContact());
       return isUpdated;
   }

    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Customer WHERE Cid = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new CustomerDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public  boolean Delete( String id) throws SQLException, ClassNotFoundException {

       Boolean isdeleted = CrudUtil.execute(" DELETE FROM customer  WHERE Cid = ? ",id);
       return isdeleted;
    }

    public String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM customer ORDER BY CAST(SUBSTRING(Cid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public ArrayList<CustomerDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from customer");
        while (resultSet.next()){

            customerDTOData.add(new CustomerDTO(resultSet.getString("id"),
            resultSet.getString("name"),
            resultSet.getString("address"),
            resultSet.getString("contact")));
        }
        return customerDTOData;
    }

    @Override
    public void play() {

    }
}
