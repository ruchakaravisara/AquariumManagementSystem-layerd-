package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageEmployeeBO  extends SuperBO {
    public boolean saveAllemployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllemployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException ;
    public EmployeeDTO searchAllemployee(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllemployee(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<EmployeeDTO> loadAllemp() throws SQLException, ClassNotFoundException ;
    public String getemployeeId() throws SQLException, ClassNotFoundException ;
}
