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
import lk.ijse.aquarium.bo.custom.ManageDilivaryBO;
import lk.ijse.aquarium.bo.impl.ManageDilivaryBOImpl;
import lk.ijse.aquarium.dto.DilivaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageDilivaryFormController {

    ManageDilivaryBO manageDilivaryBO = (ManageDilivaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DILIVARY);
    public JFXTextField txtId;
    public JFXTextField txtTime;
    public JFXTextField txtAddress;
    public TableView tblDilivary;
    public TableColumn colId;
    public TableColumn colTime;
    public TableColumn colAddress;

    public void btnAddDilivaryOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
       String time = txtTime.getText();
        String address = txtAddress.getText();


        DilivaryDTO dilivaryDTO = new DilivaryDTO(id, time, address);
        try {
            boolean isAdded = manageDilivaryBO.saveAllDilivary(dilivaryDTO);

            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Dilivary Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomers("");
    }

    public void btnUpdateDilivaryOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String time = txtTime.getText();
        String address = txtAddress.getText();


        DilivaryDTO dilivaryDTO =new DilivaryDTO(id,time,address);
        boolean isUpdated = false;
        try {
            isUpdated = manageDilivaryBO.updateAllDilivary(dilivaryDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllCustomers("");

    }

    public void btnDeleteDilivaryOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        DilivaryDTO dilivaryDTO =new DilivaryDTO(id);
        try {
            boolean isDeleted = manageDilivaryBO.deleteAllDilivary(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomers("");
    }

    public void btnSearchDilivaryOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            DilivaryDTO dilivaryDTO = manageDilivaryBO.searchAllDilivary(id);
            if(dilivaryDTO != null) {
                txtId.setText(dilivaryDTO.getId());
                txtTime.setText(String.valueOf(dilivaryDTO.getTime()));
                txtAddress.setText(dilivaryDTO.getAddress());

            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoadDilivaryOnAction(ActionEvent actionEvent) {
    }

    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));


        loadAllCustomers("");
    }

    private void loadAllCustomers(String searchID) {
        ObservableList<DilivaryDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<DilivaryDTO> dilivaryDTOData = manageDilivaryBO.loadAllDilivary();

            for (DilivaryDTO st : dilivaryDTOData) {

                DilivaryDTO s = new DilivaryDTO(st.getId(), st.getTime(), st.getAddress());
                CList.add(s);
            }

        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblDilivary.setItems(CList);
    }
    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("D00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "D00" + id;
        }
        return "D001";
    }
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(manageDilivaryBO.getDilivaryId());
        txtId.setText(nextID);
    }
}
