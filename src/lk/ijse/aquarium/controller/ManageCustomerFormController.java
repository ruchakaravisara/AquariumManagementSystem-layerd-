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
import lk.ijse.aquarium.bo.SuperBO;
import lk.ijse.aquarium.bo.custom.ManageCustomerBO;
import lk.ijse.aquarium.bo.impl.ManageCustomerBOImpl;
import lk.ijse.aquarium.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageCustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableView tblCustomer;

     //private final ManageCustomerBO customerBO =new ManageCustomerBOImpl();
     ManageCustomerBO customerBO = (ManageCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    
    public void btnAddOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
//////
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = pattern.matcher(txtName.getText());

        Pattern tel = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        Matcher telep = pattern.matcher(txtContact.getText());
        boolean isMatches = matcher.matches();
        boolean isMatch = telep.matches();
        if (isMatches && isMatch) {

        CustomerDTO customerDto = new CustomerDTO(id, name, address, contact);
        try {
            boolean isAdded = customerBO.saveAllCustomers(customerDto);

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Error!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomers();
    } else if (!isMatches) {
            txtName.setFocusColor(Paint.valueOf("RED"));
        }
        else txtContact.setFocusColor(Paint.valueOf("RED"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact =txtContact.getText();

        CustomerDTO customerDto =new CustomerDTO(id,name,address,contact);
        boolean isUpdated = false;
        try {
            isUpdated = customerBO.updateAllCustomers(customerDto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllCustomers();

    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            CustomerDTO customerDto = customerBO.searchAllCustomers(id);
            if(customerDto != null) {
                txtId.setText(customerDto.getId());
                txtName.setText(customerDto.getName());
                txtAddress.setText(customerDto.getAddress());
                txtContact.setText(customerDto.getContact());
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        CustomerDTO customerDto =new CustomerDTO(id);
        try {
            boolean isDeleted = customerBO.deleteAllCustomers(id);

            if(isDeleted) {
               new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadAllCustomers();
    }


    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerDTO> CList = FXCollections.observableArrayList();

       try {
           ArrayList<CustomerDTO> customerDTOData = customerBO.loadAllCusttomers();

           for (CustomerDTO st : customerDTOData) {

               CustomerDTO s = new CustomerDTO(st.getId(), st.getName(), st.getAddress(), st.getContact());
                    CList.add(s);

           }
       }catch (SQLException e){
           System.out.println("sql");
       }catch (ClassNotFoundException e){
           System.out.println("Class");
       }
        tblCustomer.setItems(CList);
    }

    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("C00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "C00" + id;
        }
        return "C001";
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(customerBO.getCustomerId());
        txtId.setText(nextID);
    }
}
