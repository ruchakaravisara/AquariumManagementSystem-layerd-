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
import lk.ijse.aquarium.bo.custom.ManageVehicalBO;
import lk.ijse.aquarium.bo.impl.ManageVehicalBOImpl;
import lk.ijse.aquarium.dto.VehicalDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageVehicalController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtEid;
    public TableView tblVehical;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEid;
    //private final VehicalDAO vehicalCrudDAO   = new VehicalDAOImpl();
    ManageVehicalBO manageVehicalBO = (ManageVehicalBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICAL);

    public void btnAddVehicalOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String eid = txtEid.getText();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = pattern.matcher(txtName.getText());
        boolean isMatches = matcher.matches();
        if(isMatches) {
            VehicalDTO vehicalDTO = new VehicalDTO(id, name, eid);
            try {
                boolean isAdded = manageVehicalBO.saveAllVehical(vehicalDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehical Added!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Error!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loadAllVehical();
        }else txtName.setFocusColor(Paint.valueOf("RED"));
    }

    public void btnUpdateVehicalOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String empid = txtEid.getText();


        VehicalDTO vehicalDTO =new VehicalDTO(id,name,empid);
        boolean isUpdated = false;
        try {
            isUpdated = manageVehicalBO.updateAllVehical(vehicalDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllVehical();
    }

    public void btnDeleteVehicalOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        VehicalDTO vehicalDTO =new VehicalDTO(id);
        try {
            boolean isDeleted = manageVehicalBO.deleteAllVehical(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllVehical();

    }

    public void btnSearchVehicalOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            VehicalDTO vehicalDTO = manageVehicalBO.searchAllVehical(id);
            if(vehicalDTO != null) {
                txtId.setText(vehicalDTO.getId());
                txtName.setText(vehicalDTO.getName());
                txtEid.setText(vehicalDTO.getEmpid());

            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEid.setCellValueFactory(new PropertyValueFactory<>("empid"));


        loadAllVehical();
    }

    private void loadAllVehical() {
        ObservableList<VehicalDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<VehicalDTO> vehicalDTOData = manageVehicalBO.loadAllVehical();

            for (VehicalDTO st : vehicalDTOData) {

                VehicalDTO s = new VehicalDTO(st.getId(), st.getName(), st.getEmpid());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblVehical.setItems(CList);
    }
    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("V00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "V00" + id;
        }
        return "V001";
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(manageVehicalBO.getVehicalId());
        txtId.setText(nextID);
    }
}
