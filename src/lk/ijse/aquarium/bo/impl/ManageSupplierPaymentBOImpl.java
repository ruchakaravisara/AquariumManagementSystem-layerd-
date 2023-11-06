package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageSupplierPaymentBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.SupplierPaymentDAO;
import lk.ijse.aquarium.dto.SupplierDTO;
import lk.ijse.aquarium.dto.SupplierPaymentDTO;
import lk.ijse.aquarium.entity.Supplier;
import lk.ijse.aquarium.entity.SupplierPayment;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageSupplierPaymentBOImpl implements ManageSupplierPaymentBO {
    SupplierPaymentDAO  supplierPaymentDAO= (SupplierPaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERPAYMENT);
    public boolean saveAllSupplierPayment(SupplierPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO .save(dto);
    }
    public boolean updateAllSupplierPayment(SupplierPaymentDTO dto) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO .update(dto);
    }
    public SupplierPaymentDTO searchAllSupplierPayment(String id) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO.search(id);

    }
    public boolean deleteAllSupplierPayment(String id) throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO .Delete(id);
    }
    public ArrayList<SupplierPaymentDTO> loadAllSupplierPayment() throws SQLException, ClassNotFoundException {
       return supplierPaymentDAO.getData();

    }
    public String getSupplierPayment() throws SQLException, ClassNotFoundException {
        return supplierPaymentDAO .getID();
    }
}
