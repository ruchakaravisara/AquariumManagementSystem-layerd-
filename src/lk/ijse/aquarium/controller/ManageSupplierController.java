package lk.ijse.aquarium.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.aquarium.bo.BOFactory;
import lk.ijse.aquarium.bo.custom.ManageOrdersBO;
import lk.ijse.aquarium.bo.custom.ManageSupplierBO;
import lk.ijse.aquarium.bo.impl.ManageSupplierBOImpl;
import lk.ijse.aquarium.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageSupplierController {
    ManageSupplierBO manageSupplierBO = (ManageSupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
   // private final SupplierDAO supplierCrudDAO   = new SupplierDAOImpl();
    public JFXTextField txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtName;
    public TableView tblSupplier;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact =txtContact.getText();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = pattern.matcher(txtName.getText());

        Pattern tel = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher telep = pattern.matcher(txtContact.getText());
        boolean isMatches = matcher.matches();
        boolean isMatch = telep.matches();

        if(isMatch && isMatches) {
            SupplierDTO supplierDTO = new SupplierDTO(id, name, address, contact);
            try {
                boolean isAdded = manageSupplierBO.saveAllSupplier(supplierDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Error!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loadAllSupplier();
        }else if (!isMatches) {
            txtName.setFocusColor(Paint.valueOf("RED"));
        }
        else txtContact.setFocusColor(Paint.valueOf("RED"));
    }

    public void btnUpdateSupplierOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact =txtContact.getText();

        SupplierDTO supplierDTO =new SupplierDTO(id,name,address,contact);
        boolean isUpdated = false;
        try {
            isUpdated = manageSupplierBO.updateAllSupplier(supplierDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllSupplier();

    }

    public void btnSearchSupplierOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            SupplierDTO supplierDTO = manageSupplierBO.searchAllSupplier(id);
            if(supplierDTO != null) {
                txtId.setText(supplierDTO.getId());
                txtName.setText(supplierDTO.getName());
                txtAddress.setText(supplierDTO.getAddress());
                txtContact.setText(supplierDTO.getContact());
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnLoadAllOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        SupplierDTO supplierDTO =new SupplierDTO(id);
        try {
            boolean isDeleted = manageSupplierBO.deleteAllSupplier(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllSupplier();
    }
    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<SupplierDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<SupplierDTO> supplierDTOData = manageSupplierBO.loadAllSupplier();

            for (SupplierDTO st : supplierDTOData) {

                SupplierDTO s = new SupplierDTO(st.getId(), st.getName(), st.getAddress(), st.getContact());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblSupplier.setItems(CList);
    }

    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("S00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "S00" + id;
        }
        return "S001";
    }
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(manageSupplierBO.getSupplierId());
        txtId.setText(nextID);
    }
}
