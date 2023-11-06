package lk.ijse.aquarium.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.aquarium.dao.custom.OrderDetailDAO;
import lk.ijse.aquarium.db.DBConnection;
import lk.ijse.aquarium.dto.OrderDetailsDTO;
import lk.ijse.aquarium.entity.OrderDetails;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    public  ArrayList<OrderDetailsDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> otherStockItemData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select orderDetails.Oid,\n" +
                "       Cid,\n" +
                "       Did,\n" +
                "       orderDetails.Iid,\n" +
                "       name,\n" +
                "       price,\n" +
                "       quantity\n" +
                "from orderDetails\n" +
                "         join orders o on o.Oid =orderDetails.Oid\n" +
                "         join Item i on i.Iid=orderDetails.Iid ;");
        while (rs.next()) {
            otherStockItemData.add(new OrderDetailsDTO(rs.getString("Oid"),

                    rs.getString("Cid"),
                    rs.getString("Did"),
                    rs.getString("Iid"),

                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")));
        }
        return otherStockItemData;
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM orders ORDER BY CAST(SUBSTRING(Oid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public  boolean insertNewItemTransaction(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        String oid = orderDetailsDTO.getOid();
        String cid = orderDetailsDTO.getCid();
        String did = orderDetailsDTO.getDid();
        String iid = orderDetailsDTO.getIid();
        String name = orderDetailsDTO.getName();
        double price = orderDetailsDTO.getPrice();
        int quantity = orderDetailsDTO.getQuantity();
        String date = "2019-03-03";

        Connection connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToorders = CrudUtil.execute("insert into orders (Oid, Cid, Did)\n" +
                "values (?,?,?);", oid, cid, did);
        Boolean isInsertedToorderDetails = CrudUtil.execute("insert into orderDetails (Oid, Iid, Date)\n" +
                "values (?,?,?)", oid, iid, date);

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

    @Override
    public boolean save(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  boolean update(OrderDetailsDTO orderDetailsDTO) throws SQLException, ClassNotFoundException {
        String oid = orderDetailsDTO.getOid();
        String cid = orderDetailsDTO.getCid();
        String did = orderDetailsDTO.getDid();
        String iid = orderDetailsDTO.getIid();
        String name = orderDetailsDTO.getName();
        double price = orderDetailsDTO.getPrice();
        int quantity = orderDetailsDTO.getQuantity();
        String date = "2019-03-03";

        Connection connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToOrders = CrudUtil.execute("update orders\n" +
                "set Cid = ?,Did = ? \n" +
                "where Oid = ? ",cid,did,oid);

        Boolean isInsertedToOrderDetails = CrudUtil.execute("update orderDetails\n" +
                "set Iid = ?,date =? \n" +
                "where Oid = ? ",iid,date,oid);

        if (isInsertedToOrderDetails && isInsertedToOrders) {
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

    @Override
    public OrderDetailsDTO search(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM item WHERE Iid=?",id);
    }

    public  boolean Delete(String Oid) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();

        connection.setAutoCommit(false);

        Boolean isDeletedFromOrders = CrudUtil.execute("delete from orders where Oid = ? " ,Oid);
        Boolean isDeletedFromOrderDetails = CrudUtil.execute("delete from orderDetails where Oid = ? " ,Oid);

        if (isDeletedFromOrderDetails && isDeletedFromOrders) {
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
    ////////////////////////////
    public  ArrayList<String> loadItemCodes() throws SQLException, ClassNotFoundException {
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
    /*public  Item seach(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT * FROM item WHERE Iid=?",id);
    }
*/


}
