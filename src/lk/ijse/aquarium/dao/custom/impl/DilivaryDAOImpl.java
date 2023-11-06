package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.DilivaryDAO;
import lk.ijse.aquarium.dto.DilivaryDTO;
import lk.ijse.aquarium.entity.Dilivary;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DilivaryDAOImpl implements DilivaryDAO {
    public  boolean save(DilivaryDTO dilivaryDTO) throws SQLException, ClassNotFoundException {
       Boolean issaveDelivary = CrudUtil.execute("INSERT INTO Dilivary VALUES (?, ?, ? )", dilivaryDTO.getId(), dilivaryDTO.getTime(), dilivaryDTO.getAddress());
        return issaveDelivary;

    }


    public  boolean update(DilivaryDTO dilivaryDTO) throws SQLException, ClassNotFoundException {

        Boolean isupdateDilivary = CrudUtil.execute(" UPDATE dilivary SET time = ? , address = ? WHERE Did = ? ", dilivaryDTO.getTime(), dilivaryDTO.getAddress());
        return isupdateDilivary;
    }

    public DilivaryDTO search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM dilivary WHERE Did = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new DilivaryDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)

            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" DELETE FROM dilivary  WHERE Did = ? ",id);
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM dilivary ORDER BY CAST(SUBSTRING(Did, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public  ArrayList<DilivaryDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<DilivaryDTO> dilivaryDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from dilivary");
        while (resultSet.next()){

            dilivaryDTOData.add(new DilivaryDTO(resultSet.getString("id"),
                    resultSet.getString("time"),
                    resultSet.getString("address")));

        }
        return dilivaryDTOData;
    }

    @Override
    public void play() {

    }
}
