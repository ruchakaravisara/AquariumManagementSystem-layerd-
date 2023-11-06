package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageItemBO extends SuperBO {
    public boolean saveAllitem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllitem(ItemDTO dto) throws SQLException, ClassNotFoundException ;
    public ItemDTO searchAllitem(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllitem(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<ItemDTO> loadAllitem() throws SQLException, ClassNotFoundException ;
    public String getitemId() throws SQLException, ClassNotFoundException ;
}
