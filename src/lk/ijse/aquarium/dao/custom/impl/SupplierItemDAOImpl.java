package lk.ijse.aquarium.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.aquarium.dao.custom.SupplierItemDAO;
import lk.ijse.aquarium.db.DBConnection;
import lk.ijse.aquarium.dto.SupplierItemDTO;
import lk.ijse.aquarium.entity.SupplierItem;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierItemDAOImpl implements SupplierItemDAO {
    public  ArrayList<SupplierItemDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierItemDTO> otherStockItemData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select supplyingItems.Sid,\n" +
                "        s.name,\n" +
                "       address,\n" +
                "       contact,\n" +
                "       supplyingItems.Iid,\n" +
                "        i.name,\n" +
                "       price,\n" +
                "       quantity\n" +
                "from supplyingItems\n" +
                "         join supplier s on s.Sid =supplyingItems.Sid\n" +
                "         join Item i on i.Iid=supplyingItems.Iid ;");
        while (rs.next()) {
            otherStockItemData.add(new SupplierItemDTO(rs.getString("Sid"),

                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("contact"),
                    rs.getString("Iid"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")));
        }
        return otherStockItemData;
    }

    @Override
    public boolean save(SupplierItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplierItemDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupplierItemDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean Delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM supplier ORDER BY CAST(SUBSTRING(Sid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    public  boolean insertSupplierTransaction(SupplierItemDTO supplierItemDTO) throws SQLException, ClassNotFoundException {
        String Sid = supplierItemDTO.getSid();
        String name = supplierItemDTO.getName();
        String address = supplierItemDTO.getAddress();
        String contact = supplierItemDTO.getContact();
        String Iid = supplierItemDTO.getIid();
        String ItemName = supplierItemDTO.getName();
        double price = supplierItemDTO.getPrice();
        int quantity = supplierItemDTO.getQuantity();


        Connection connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToorders = CrudUtil.execute("insert into supplier (Sid, name, address,contact)\n" +
                "values (?,?,?,?);", Sid, name, address,contact);
        Boolean isInsertedToorderDetails = CrudUtil.execute("insert into supplyingItems (Sid, Iid)\n" +
                "values (?,?)", Sid,Iid);
        /*Boolean isInsertedItemsDetails = CrudUtil.execute("insert into item (Iid, name,price,quantity)\n" +
                "values (?,?,?,?)", Iid,name,price,quantity);*/

        if (isInsertedToorders && isInsertedToorderDetails) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "rollback !").show();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
   /*public static ArrayList<String> loadSupplierCodes() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Sid FROM supplier");
        ArrayList<String> codes = new ArrayList<>();

        while(result.next()) {
            codes.add(result.getString(1));
        }
        return codes;
    }
    public static ResultSet getSuppdetailss(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM supplier WHERE Sid=?",id);
    }*/
    /*public Item search(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM item WHERE Iid=?",id);
    }*/
    public ArrayList<String> loadIteCodes() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Iid FROM item");
        ArrayList<String> codes = new ArrayList<>();

        while(result.next()) {
            codes.add(result.getString(1));
        }
        return codes;
    }

    @Override
    public void play() {

    }
}
