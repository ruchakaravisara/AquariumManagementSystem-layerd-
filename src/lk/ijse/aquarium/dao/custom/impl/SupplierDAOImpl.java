package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.SupplierDAO;
import lk.ijse.aquarium.dto.SupplierDTO;
import lk.ijse.aquarium.entity.Supplier;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl  implements SupplierDAO {
    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Supplier VALUES (?, ?, ?, ?)", supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getContact());


    }


    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" UPDATE supplier SET name = ? , address = ? , contact = ? WHERE Sid = ? " , supplierDTO.getName(), supplierDTO.getAddress(), supplierDTO.getContact());
    }

    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT  * FROM Supplier WHERE Sid = ?", id);

        if(result.next()) {
            return new SupplierDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" DELETE FROM supplier  WHERE Sid = ? ",id);
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM supplier ORDER BY CAST(SUBSTRING(Sid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    public  ArrayList<SupplierDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        while (resultSet.next()){

            supplierDTOData.add(new SupplierDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return supplierDTOData;
    }

    @Override
    public void play() {

    }
}
