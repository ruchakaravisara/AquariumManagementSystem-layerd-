package lk.ijse.aquarium.dao.custom.impl;

import lk.ijse.aquarium.dao.custom.OrdersDAO;
import lk.ijse.aquarium.dto.OrdersDTO;
import lk.ijse.aquarium.entity.Orders;
import lk.ijse.aquarium.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    public  boolean save(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO Orders VALUES (?, ?, ?)", ordersDTO.getId(), ordersDTO.getCid(), ordersDTO.getDid());


    }


    public  boolean update(OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(" UPDATE orders SET cid = ? , did = ? WHERE Oid = ? ", ordersDTO.getCid(), ordersDTO.getDid());
    }

    public OrdersDTO search(String id) throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT  * FROM Orders WHERE Oid = ?", id);

        if(result.next()) {
            return new OrdersDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3)

            );
        }
        return null;
    }

    public  boolean Delete(String id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute(" DELETE FROM Orders  WHERE Oid = ? ",id);
    }
    public  String getID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM orders ORDER BY CAST(SUBSTRING(Oid, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public  ArrayList<OrdersDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<OrdersDTO> orderData = new ArrayList<>();

        ResultSet resultSet = CrudUtil.execute("select * from orders");
        while (resultSet.next()){

            orderData.add(new OrdersDTO(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
        }
        return orderData;
    }

    @Override
    public void play() {

    }
}
