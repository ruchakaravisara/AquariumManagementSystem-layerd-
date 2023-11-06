package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.VehicalDAO;
import lk.ijse.aquarium.dto.VehicalDTO;
import lk.ijse.aquarium.entity.Vehical;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicalDAOImpl implements VehicalDAO {
    public  boolean save(VehicalDTO vehicalDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Vehical VALUES (?, ?, ?)", vehicalDTO.getId(), vehicalDTO.getName(), vehicalDTO.getEmpid());


    }


    public  boolean update(VehicalDTO vehicalDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" update Vehical set name = ? , empid = ? , WHERE Vid = ?", vehicalDTO.getName(), vehicalDTO.getEmpid());
    }

    public VehicalDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT  * FROM Vehical WHERE Vid = ?", id);

        if(result.next()) {
            return new VehicalDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)

            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute( " DELETE FROM Vehical  WHERE Vid = ? ",id);
    }
    public String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM vehical ORDER BY CAST(SUBSTRING(Vid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public ArrayList<VehicalDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<VehicalDTO> vehicalDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from vehical");
        while (resultSet.next()){

            vehicalDTOData.add(new VehicalDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
        }
        return vehicalDTOData;
    }

    @Override
    public void play() {

    }
}
