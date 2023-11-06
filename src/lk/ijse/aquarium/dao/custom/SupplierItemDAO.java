package lk.ijse.aquarium.dao.custom;

import lk.ijse.aquarium.dao.CrudDAO;
import lk.ijse.aquarium.dto.SupplierItemDTO;
import lk.ijse.aquarium.entity.SupplierItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierItemDAO extends CrudDAO<SupplierItemDTO> {
  //  public  ArrayList<SupplierItem> getItemsData() throws SQLException, ClassNotFoundException ;

    public boolean insertSupplierTransaction(SupplierItemDTO supplierItemDTO) throws SQLException, ClassNotFoundException ;


    public ArrayList<String> loadIteCodes() throws SQLException, ClassNotFoundException ;
}
