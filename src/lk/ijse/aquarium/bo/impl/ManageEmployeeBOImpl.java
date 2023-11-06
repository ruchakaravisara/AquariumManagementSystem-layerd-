package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageEmployeeBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.EmployeeDAO;
import lk.ijse.aquarium.dto.DilivaryDTO;
import lk.ijse.aquarium.dto.EmployeeDTO;
import lk.ijse.aquarium.entity.Dilivary;
import lk.ijse.aquarium.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageEmployeeBOImpl implements ManageEmployeeBO {
    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);


    public boolean saveAllemployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO .save(dto);
    }
    public boolean updateAllemployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO .update(dto);
    }
    public EmployeeDTO searchAllemployee(String id) throws SQLException, ClassNotFoundException {
    return employeeDAO.search(id);

    }
    public boolean deleteAllemployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO .Delete(id);
    }
    public ArrayList<EmployeeDTO> loadAllemp() throws SQLException, ClassNotFoundException {

         return employeeDAO .getData();
    }
    public String getemployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO .getID();
    }
}
