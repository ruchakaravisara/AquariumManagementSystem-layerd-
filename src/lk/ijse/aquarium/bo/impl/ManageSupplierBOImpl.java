package lk.ijse.aquarium.bo.impl;

import lk.ijse.aquarium.bo.custom.ManageSupplierBO;
import lk.ijse.aquarium.dao.DAOFactory;
import lk.ijse.aquarium.dao.custom.SupplierDAO;
import lk.ijse.aquarium.dto.OrdersDTO;
import lk.ijse.aquarium.dto.SupplierDTO;
import lk.ijse.aquarium.entity.Orders;
import lk.ijse.aquarium.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageSupplierBOImpl implements ManageSupplierBO {
    SupplierDAO supplierDAO= (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    public boolean saveAllSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO .save(dto);
    }
    public boolean updateAllSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO .update(dto);
    }
    public SupplierDTO searchAllSupplier(String id) throws SQLException, ClassNotFoundException {
      return supplierDAO.search(id);

    }
    public boolean deleteAllSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO .Delete(id);
    }
    public ArrayList<SupplierDTO> loadAllSupplier() throws SQLException, ClassNotFoundException {
      return supplierDAO.getData();

    }
    public String getSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO .getID();
    }
}
