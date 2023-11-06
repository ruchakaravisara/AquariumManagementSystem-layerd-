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
import lk.ijse.aquarium.bo.custom.ManageCustomerBO;
import lk.ijse.aquarium.bo.custom.ManageCustomerPaymentBO;
import lk.ijse.aquarium.bo.impl.ManageCustomerPaymentBOBOImpl;
import lk.ijse.aquarium.dto.CustomerPaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerPaymentFormController {

    public TableView tableCustomerPayment;
    public TableColumn colPaymentId;
    public TableColumn colValue;
    public TableColumn colCusId;
    public JFXTextField txtcustomer;
    public JFXTextField txtValue;
    public JFXTextField txtId;
    ManageCustomerPaymentBO customerBO = (ManageCustomerPaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMERPAYMENT);
    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Double value = Double.valueOf(txtValue.getText());
        String cid = txtcustomer.getText();


        CustomerPaymentDTO customerPaymentDTO = new CustomerPaymentDTO(id, value, cid);
        try {
            boolean isAdded = customerBO. saveAllCustomerspay(customerPaymentDTO);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Pay Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomerPayment();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Double value = Double.valueOf(txtValue.getText());
        String cid = txtcustomer.getText();


        CustomerPaymentDTO customerPaymentDTO =new CustomerPaymentDTO(id,value,cid);
        boolean isUpdated = false;
        try {
            isUpdated = customerBO.updateAllCustomersPay(customerPaymentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllCustomerPayment();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        CustomerPaymentDTO customerPaymentDTO =new CustomerPaymentDTO(id);
        try {
            boolean isDeleted = customerBO.deleteAllCustomersPay(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomerPayment();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            CustomerPaymentDTO customerPaymentDTO = customerBO.searchAllCustomersPay(id);
            if(customerPaymentDTO != null) {
                txtId.setText(customerPaymentDTO.getId());
                txtValue.setText(String.valueOf(customerPaymentDTO.getValue()));
                txtcustomer.setText(customerPaymentDTO.getcId());

            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize()  {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cId"));


        loadAllCustomerPayment();
    }

    private void loadAllCustomerPayment() {
        ObservableList<CustomerPaymentDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerPaymentDTO> customerrData = customerBO.loadAllCusttomersPay();

            for (CustomerPaymentDTO st : customerrData) {

                CustomerPaymentDTO s = new CustomerPaymentDTO(st.getId(), st.getValue(), st.getcId());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tableCustomerPayment.setItems(CList);
    }
}
