package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.SupplierPaymentDAO;
import lk.ijse.aquarium.dto.SupplierPaymentDTO;
import lk.ijse.aquarium.entity.SupplierPayment;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierPaymentDAOImpl  implements SupplierPaymentDAO {
    public  boolean save(SupplierPaymentDTO supplierPaymentDTO) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO supplierpayment VALUES (?, ?, ?)", supplierPaymentDTO.getId(), supplierPaymentDTO.getValue(), supplierPaymentDTO.getSid());


    }


    public boolean update(SupplierPaymentDTO supplierPaymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" UPDATE supplierpayment SET value = ? , sid = ? WHERE SPid = ? ", supplierPaymentDTO.getValue(), supplierPaymentDTO.getSid());
    }

    public SupplierPaymentDTO search(String id) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT  * FROM supplierpayment WHERE SPid = ?", id);

        if(result.next()) {
            return new SupplierPaymentDTO(
                    result.getString(1),
                    result.getDouble(2),
                    result.getString(3)
            );
        }
        return null;
    }

    public boolean Delete( String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(" DELETE FROM supplierpayment  WHERE SPid = ? ",id);
    }

    @Override
    public String getID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public ArrayList<SupplierPaymentDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierPaymentDTO> supplierpayment = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from supplierpayment");
        while (resultSet.next()){

            supplierpayment.add(new SupplierPaymentDTO(resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3)));
        }
        return supplierpayment;
    }

    @Override
    public void play() {

    }
}
