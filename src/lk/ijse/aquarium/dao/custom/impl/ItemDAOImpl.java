package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.ItemDAO;
import lk.ijse.aquarium.dto.ItemDTO;
import lk.ijse.aquarium.entity.Item;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    public  boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Item VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(), itemDTO.getQuantity());


    }


    public  boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        String sql = " UPDATE Item SET name = ? , price = ? , quantity = ? WHERE Iid = ? " ;
        return CrudUtil.execute(sql, itemDTO.getName(), itemDTO.getPrice(), itemDTO.getQuantity());
    }

    public ItemDTO search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM Item WHERE Iid = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new ItemDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getInt(4)
            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" DELETE FROM item  WHERE Iid = ? ",id);
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item ORDER BY CAST(SUBSTRING(Iid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public  ArrayList<ItemDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from item");
        while (resultSet.next()){

            itemDTOData.add(new ItemDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)));

        }
        return itemDTOData;
    }

    @Override
    public void play() {

    }
}
