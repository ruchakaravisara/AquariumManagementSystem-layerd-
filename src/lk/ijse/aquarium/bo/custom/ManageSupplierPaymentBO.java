package lk.ijse.aquarium.bo.custom;

import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.dto.SupplierPaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManageSupplierPaymentBO extends SuperBO {
    public boolean saveAllSupplierPayment(SupplierPaymentDTO dto) throws SQLException, ClassNotFoundException ;
    public boolean updateAllSupplierPayment(SupplierPaymentDTO dto) throws SQLException, ClassNotFoundException ;
    public SupplierPaymentDTO searchAllSupplierPayment(String id) throws SQLException, ClassNotFoundException ;
    public boolean deleteAllSupplierPayment(String id) throws SQLException, ClassNotFoundException ;
    public ArrayList<SupplierPaymentDTO> loadAllSupplierPayment() throws SQLException, ClassNotFoundException ;
    public String getSupplierPayment() throws SQLException, ClassNotFoundException ;
}
