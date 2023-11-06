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
import lk.ijse.aquarium.bo.custom.ManageItemBO;
import lk.ijse.aquarium.bo.impl.ManageItemBOImpl;
import lk.ijse.aquarium.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageItemFormController {
    ManageItemBO manageItemBO = (ManageItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);


    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtQuantity;
    public TableView tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colPrice;
    public TableColumn colQuantity;

    public void btnAddItemOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        double price = Double.parseDouble((txtPrice.getText()));
        int quantity = Integer.parseInt((txtQuantity.getText()));

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        Matcher matcher = pattern.matcher(txtName.getText());
        boolean isMatches = matcher.matches();
        if(isMatches) {

            ItemDTO itemDTO = new ItemDTO(id, name, price, quantity);
            try {
                boolean isAdded = manageItemBO.saveAllitem(itemDTO);

                if (isAdded) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Added!").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Error!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            loadAllItem();
        } else txtName.setFocusColor(Paint.valueOf("RED"));
    }

    public void btnUpdateItemOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        double price = Double.parseDouble((txtPrice.getText()));
        int quantity = Integer.parseInt((txtQuantity.getText()));

        ItemDTO itemDTO =new ItemDTO(id,name,price,quantity);
        boolean isUpdated = false;
        try {
            isUpdated = manageItemBO.updateAllitem(itemDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else
                new Alert(Alert.AlertType.ERROR, " Not Updated!").show();
        } catch (SQLException e) {
            System.out.println("SQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Class");
        }
        loadAllItem();
    }

    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        ItemDTO itemDTO =new ItemDTO(id);
        try {
            boolean isDeleted = manageItemBO.deleteAllitem(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        loadAllItem();
    }

    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            ItemDTO itemDTO = manageItemBO.searchAllitem(id);
            if(itemDTO != null) {
                txtId.setText(itemDTO.getId());
                txtName.setText(itemDTO.getName());
                txtPrice.setText(String.valueOf(itemDTO.getPrice()));
                txtQuantity.setText(String.valueOf(itemDTO.getQuantity()));
            }
            else new Alert(Alert.AlertType.ERROR,"NOT FOUND!").show();
        } catch (SQLException  e) {
            System.out.println("Sql");
        }catch (ClassNotFoundException e){
            System.out.println("class");
        }
    }

    public void initialize()  {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadAllItem();
    }

    private void loadAllItem() {
        ObservableList<ItemDTO> CList = FXCollections.observableArrayList();

        try {
            ArrayList<ItemDTO> itemDTOData = manageItemBO.loadAllitem();

            for (ItemDTO st : itemDTOData) {

                ItemDTO s = new ItemDTO(st.getId(), st.getName(), st.getPrice(), st.getQuantity());
                CList.add(s);

            }
        }catch (SQLException e){
            System.out.println("sql");
        }catch (ClassNotFoundException e){
            System.out.println("Class");
        }
        tblItem.setItems(CList);
    }
    private String generateNextStockItemID(String currentItemID) {
        System.out.println(currentItemID);
        if (currentItemID != null) {
            String[] ids = currentItemID.split("I00");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "I00" + id;
        }
        return "I001";
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(manageItemBO.getitemId());
        txtId.setText(nextID);
    }
}
