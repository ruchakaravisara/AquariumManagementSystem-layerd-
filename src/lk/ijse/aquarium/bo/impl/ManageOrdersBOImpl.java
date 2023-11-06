package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageOrdersBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.OrdersDAO;
import lk.ijse.aquarium.dto.ItemDTO;
import lk.ijse.aquarium.dto.OrdersDTO;
import lk.ijse.aquarium.entity.Item;
import lk.ijse.aquarium.entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageOrdersBOImpl implements ManageOrdersBO {
    OrdersDAO ordersDAO= (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    public boolean saveAllOrders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.save(dto);
    }
    public boolean updateAllorders(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.update(dto);
    }
    public OrdersDTO searchAllorders(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.search(id);

    }
    public boolean deleteAllorders(String id) throws SQLException, ClassNotFoundException {
        return ordersDAO.Delete(id);
    }
    public ArrayList<OrdersDTO> loadAllorders() throws SQLException, ClassNotFoundException {
       return ordersDAO.getData();

    }
    public String getordersId() throws SQLException, ClassNotFoundException {
        return ordersDAO.getID();
    }
}
