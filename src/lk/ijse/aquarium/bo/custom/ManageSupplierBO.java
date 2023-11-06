package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageSupplierBO extends SuperBO {
    public boolean saveAllSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException ;
    public SupplierDTO searchAllSupplier(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllSupplier(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<SupplierDTO> loadAllSupplier() throws SQLException, ClassNotFoundException ;
    public String getSupplierId() throws SQLException, ClassNotFoundException ;
}
