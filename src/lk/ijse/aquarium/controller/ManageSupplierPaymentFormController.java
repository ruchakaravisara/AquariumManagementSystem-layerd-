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
import lk.ijse.aquarium.bo.custom.ManageOrdersBO;
import lk.ijse.aquarium.bo.custom.ManageSupplierPaymentBO;
import lk.ijse.aquarium.bo.impl.ManageSupplierPaymentBOImpl;
import lk.ijse.aquarium.dto.SupplierPaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageSupplierPaymentFormController {
    ManageSupplierPaymentBO manageSupplierBO = (ManageSupplierPaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIERPAYMENT);
   // private final SupplierPaymentDAO supplierPaymentCrudDAO   = new SupplierPaymentDAOImpl();
    public JFXTextField txtId;
    public JFXTextField txtValue;
    public JFXTextField txtSid;
    public TableView tblSupplierPayment;
    public TableColumn colSPayId;
    public TableColumn colValue;
    public TableColumn colSupplierId;

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Double value = Double.valueOf(txtValue.getText());
        String sid = txtSid.getText();


        SupplierPaymentDTO supplierPaymentDTO = new SupplierPaymentDTO(id, value, sid);
        try {
            boolean isAdded = manageSupplierBO.saveAllSupplierPayment(supplierPaymentDTO);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Pay Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSupplierPayment();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        Double value = Double.valueOf(txtValue.getText());
        String sid = txtSid.getText();

       SupplierPaymentDTO supplierPaymentDTO =new SupplierPaymentDTO(id,value,sid);
        boolean isUpdated = false;
        try {
            isUpdated = manageSupplierBO.updateAllSupplierPayment(supplierPaymentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllSupplierPayment();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        SupplierPaymentDTO supplierPaymentDTO =new SupplierPaymentDTO(id);
        try {
            boolean isDeleted = manageSupplierBO.deleteAllSupplierPayment(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSupplierPayment();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            SupplierPaymentDTO supplierPaymentDTO = manageSupplierBO.searchAllSupplierPayment(id);
            if(supplierPaymentDTO != null) {
                txtId.setText(supplierPaymentDTO.getId());
                txtValue.setText(String.valueOf(supplierPaymentDTO.getValue()));
                txtSid.setText(supplierPaymentDTO.getSid());

            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize()  {
        colSPayId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("sid"));


        loadAllSupplierPayment();
    }

    private void loadAllSupplierPayment() {
        ObservableList<SupplierPaymentDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierPaymentDTO> supplierData = manageSupplierBO.loadAllSupplierPayment();

            for (SupplierPaymentDTO st : supplierData) {

               SupplierPaymentDTO s = new SupplierPaymentDTO(st.getId(), st.getValue(), st.getSid());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblSupplierPayment.setItems(CList);
    }
}
