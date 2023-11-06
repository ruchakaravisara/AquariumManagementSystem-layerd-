package lk.ijse.aquarium.dao.custom;

import lk.ijse.aquarium.dao.CrudDAO;
import lk.ijse.aquarium.dto.OrderDetailsDTO;
import lk.ijse.aquarium.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDAO  extends CrudDAO<OrderDetailsDTO> {
   // public ArrayList<OrderDetails> getItemsData() throws SQLException, ClassNotFoundException ;


    public  boolean insertNewItemTransaction(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException ;
   // public  boolean updateOrderItem(OrderDetails orderDetails) throws SQLException, ClassNotFoundException ;

  //  public  Boolean deleteOrderDetailTransactions(String Oid) throws SQLException, ClassNotFoundException ;
    ////////////////////////////
    public  ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException ;

}
