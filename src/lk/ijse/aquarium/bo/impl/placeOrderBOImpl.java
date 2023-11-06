package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.dao.custom.ItemDAO;
import lk.ijse.aquarium.dao.custom.OrderDetailDAO;
import lk.ijse.aquarium.dao.custom.OrdersDAO;
import lk.ijse.aquarium.dao.custom.impl.ItemDAOImpl;
import lk.ijse.aquarium.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.aquarium.dao.custom.impl.OrdersDAOImpl;
import lk.ijse.aquarium.dto.CustomerDTO;
import lk.ijse.aquarium.dto.ItemDTO;
import lk.ijse.aquarium.dto.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class placeOrderBOImpl {
    OrderDetailDAO orderDetailDAO =new OrderDetailDAOImpl();
    private final ItemDAO itemCrudDAO= new ItemDAOImpl();
    private final OrdersDAO ordersCrudDAO = new OrdersDAOImpl();
    public boolean saveAllorderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save(dto);
    }
    public boolean updateAllorderDetails(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.update(dto);
    }
    public ItemDTO searchAllorderDetails(String id) throws SQLException, ClassNotFoundException {
        return itemCrudDAO.search(id);
    }
    public boolean deleteAllorderDetails(String id) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.Delete(id);
    }
    public ArrayList<OrderDetailsDTO> loadAllCorderDetails() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.getData();
    }
    public String getorderDetailsId() throws SQLException, ClassNotFoundException {
        return ordersCrudDAO.getID();
    }
}
