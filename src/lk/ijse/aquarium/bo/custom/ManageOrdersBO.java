package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageOrdersBO extends SuperBO {
    public boolean saveAllOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllorders(OrdersDTO dto) throws SQLException, ClassNotFoundException ;
    public OrdersDTO searchAllorders(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllorders(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<OrdersDTO> loadAllorders() throws SQLException, ClassNotFoundException ;
    public String getordersId() throws SQLException, ClassNotFoundException ;
}
