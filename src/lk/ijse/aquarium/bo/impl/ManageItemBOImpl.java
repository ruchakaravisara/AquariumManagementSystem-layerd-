package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.EmployeeDAO;
import lk.ijse.aquarium.dao.custom.ItemDAO;
import lk.ijse.aquarium.dao.custom.impl.ItemDAOImpl;
import lk.ijse.aquarium.dto.CustomerDTO;
import lk.ijse.aquarium.dto.EmployeeDTO;
import lk.ijse.aquarium.dto.ItemDTO;
import lk.ijse.aquarium.entity.Employee;
import lk.ijse.aquarium.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageItemBOImpl {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    public boolean saveAllitem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(dto);
    }
    public boolean updateAllitem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(dto);
    }
    public ItemDTO searchAllitem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
    public boolean deleteAllitem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.Delete(id);
    }
    public ArrayList<ItemDTO> loadAllitem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO>allitem =new ArrayList<>() ;
        ArrayList<ItemDTO> all = itemDAO.getData();
        for (ItemDTO emp:all){
            allitem.add(new ItemDTO(emp.getId(),emp.getName(),emp.getPrice(),emp.getQuantity()));
        }

        return allitem;
        // return itemDAO.getData();
    }
    public String getitemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getID();
    }
}
