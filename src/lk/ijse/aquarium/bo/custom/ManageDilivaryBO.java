package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.DilivaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageDilivaryBO extends SuperBO {
    public boolean saveAllDilivary(DilivaryDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllDilivary(DilivaryDTO dto) throws SQLException, ClassNotFoundException ;
    public DilivaryDTO searchAllDilivary(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllDilivary(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<DilivaryDTO> loadAllDilivary() throws SQLException, ClassNotFoundException ;
    public String getDilivaryId() throws SQLException, ClassNotFoundException;
}
