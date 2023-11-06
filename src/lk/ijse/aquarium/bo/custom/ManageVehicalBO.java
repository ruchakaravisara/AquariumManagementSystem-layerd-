package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.VehicalDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageVehicalBO  extends SuperBO {
    public boolean saveAllVehical(VehicalDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllVehical(VehicalDTO dto) throws SQLException, ClassNotFoundException ;
    public VehicalDTO searchAllVehical(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllVehical(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<VehicalDTO> loadAllVehical() throws SQLException, ClassNotFoundException ;
    public String getVehicalId() throws SQLException, ClassNotFoundException ;
}
