package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.EmployeeDAO;
import lk.ijse.aquarium.dto.EmployeeDTO;
import lk.ijse.aquarium.entity.Employee;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact());


    }


    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(" UPDATE employee SET name = ? , address = ? , contact = ? WHERE Eid = ? ", employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getContact());
    }

    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Employee WHERE Eid = ?";
        ResultSet result = CrudUtil.execute(sql, id);

        if(result.next()) {
            return new EmployeeDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {
        String sql = " DELETE FROM employee  WHERE Eid = ? " ;
        return CrudUtil.execute(sql,id);
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY CAST(SUBSTRING(Eid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public  ArrayList<EmployeeDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from employee");
        while (resultSet.next()){

            employeeDTOData.add(new EmployeeDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return employeeDTOData;
    }

    @Override
    public void play() {

    }
}
