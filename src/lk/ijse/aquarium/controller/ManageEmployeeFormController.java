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
import lk.ijse.aquarium.bo.custom.ManageDilivaryBO;
import lk.ijse.aquarium.bo.custom.ManageEmployeeBO;
import lk.ijse.aquarium.bo.impl.ManageEmployeeBOImpl;
import lk.ijse.aquarium.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageEmployeeFormController {
    ManageEmployeeBO manageEmployeeBO = (ManageEmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    //private final EmployeeDAO employeeCrudDAO = new EmployeeDAOImpl();
    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = pattern.matcher(txtName.getText());

        Pattern tel = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher telep = pattern.matcher(txtContact.getText());
        boolean isMatches = matcher.matches();
        boolean isMatch = telep.matches();

        if (isMatch && isMatches) {


        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, address, contact);
        try {
            boolean isAdded = manageEmployeeBO.saveAllemployee(employeeDTO);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllEmployee();
    }else if (!isMatches) {
            txtName.setFocusColor(Paint.valueOf("RED"));
        }
        else txtContact.setFocusColor(Paint.valueOf("RED"));
    }

    public void btnUpdateEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact =txtContact.getText();

        EmployeeDTO employeeDTO =new EmployeeDTO(id,name,address,contact);
        boolean isUpdated = false;
        try {
            isUpdated = manageEmployeeBO.updateAllemployee(employeeDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllEmployee();
    }

    public void btnDeleteEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        EmployeeDTO employeeDTO =new EmployeeDTO(id);
        try {
            boolean isDeleted = manageEmployeeBO.deleteAllemployee(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllEmployee();

    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            EmployeeDTO employeeDTO = manageEmployeeBO.searchAllemployee(id);
            if(employeeDTO != null) {
                txtId.setText(employeeDTO.getId());
                txtName.setText(employeeDTO.getName());
                txtAddress.setText(employeeDTO.getAddress());
                txtContact.setText(employeeDTO.getContact());
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllEmployee();
    }

    private void loadAllEmployee() {
        ObservableList<EmployeeDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<EmployeeDTO> employeeDTOData = manageEmployeeBO.loadAllemp();

            for (EmployeeDTO st : employeeDTOData) {

                EmployeeDTO s = new EmployeeDTO(st.getId(), st.getName(), st.getAddress(), st.getContact());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblEmployee.setItems(CList);

    }


    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("E00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "E00" + id;
        }
        return "E001";
    }
    public void txtContact(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(manageEmployeeBO.getemployeeId());
        txtId.setText(nextID);
    }
}
