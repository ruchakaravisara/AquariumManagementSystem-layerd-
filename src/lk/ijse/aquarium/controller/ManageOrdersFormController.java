package lk.ijse.aquarium.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.aquarium.bo.BOFactory;
import lk.ijse.aquarium.bo.custom.ManageDilivaryBO;
import lk.ijse.aquarium.bo.custom.ManageOrdersBO;
import lk.ijse.aquarium.bo.impl.ManageOrdersBOImpl;
import lk.ijse.aquarium.dto.OrdersDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageOrdersFormController {

    ManageOrdersBO ordersBO = (ManageOrdersBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    public JFXTextField txtId;
    public JFXTextField txtTime;
    public JFXTextField txtCusId;
    public JFXTextField txtDiliId;
    public TableView tblOrder;
    public TableColumn colId;
    public TableColumn colCusId;
    public TableColumn colDid;
    public TableColumn colTime;

    public void btnAddOrdersOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        String cid = txtCusId.getText();
        String did =txtDiliId.getText();

        OrdersDTO ordersDTO = new OrdersDTO(id , cid, did);
        try {
            boolean isAdded = ordersBO.saveAllOrders(ordersDTO);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllOrders();
    }

    public void btnUpdateOrdersOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String cid = txtCusId.getText();
        String did =txtDiliId.getText();

        OrdersDTO ordersDTO =new OrdersDTO(id,cid,did);
        boolean isUpdated = false;
        try {
            isUpdated = ordersBO.updateAllorders(ordersDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }

        loadAllOrders();
    }

    public void btnDeleteOrderOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        OrdersDTO ordersDTO =new OrdersDTO(id);
        try {
            boolean isDeleted = ordersBO.deleteAllorders(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllOrders();
    }

    public void btnSearchOrderOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            OrdersDTO ordersDTO = ordersBO.searchAllorders(id);
            if(ordersDTO != null) {
                txtId.setText(ordersDTO.getId());
                txtCusId.setText(ordersDTO.getCid());
                txtDiliId.setText(ordersDTO.getDid());
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("did"));

        loadAllOrders();
    }

    private void loadAllOrders() {
        ObservableList<OrdersDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<OrdersDTO> orderData = ordersBO.loadAllorders();

            for (OrdersDTO st : orderData) {

                OrdersDTO s = new OrdersDTO(st.getId(), st.getCid(), st.getDid());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblOrder.setItems(CList);
    }
    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("O00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "O00" + id;
        }
        return "O001";
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(ordersBO.getordersId());
        txtId.setText(nextID);
    }
}
