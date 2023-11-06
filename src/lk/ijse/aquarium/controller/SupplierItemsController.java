package lk.ijse.aquarium.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.aquarium.dao.custom.ItemDAO;
import lk.ijse.aquarium.dao.custom.SupplierDAO;
import lk.ijse.aquarium.dao.custom.SupplierItemDAO;
import lk.ijse.aquarium.dao.custom.impl.ItemDAOImpl;
import lk.ijse.aquarium.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.aquarium.dao.custom.impl.SupplierItemDAOImpl;
import lk.ijse.aquarium.dto.SupplierItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierItemsController {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtIid;
    public JFXTextField txtSid;
    public JFXTextField txtItemName;
    public Label txtPrice;
    public JFXTextField txtOid31;
    public JFXTextField txtQuantity;
    public TableColumn colSid;
    public TableColumn colSupplierName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colIid;
    public TableColumn colItemName;
    public TableColumn colPrice;
    public TableColumn colQuantity;
    public TableView tblSupplierItem;
    public JFXTextField txtSearch;
    public Label txtqq;
    public JFXTextField txtPric;
    public JFXComboBox combSupplierD;
    public JFXComboBox combIiDD;

       SupplierItemDAO supplierItemCrudDAODAO =new SupplierItemDAOImpl();
    private final SupplierDAO supplierCrudDAO   = new SupplierDAOImpl();
    private final ItemDAO itemCrudDAO= new ItemDAOImpl();
    public void initialize() {

        colSid.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colIid.setCellValueFactory(new PropertyValueFactory<>("Iid"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        setRegIdS();
       // setRegIdSss();

        tblSupplierItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData((SupplierItemDTO) newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStockData(newValue);

        });

        loadStockData("");
    }

    private void setData(SupplierItemDTO newValue) {
        txtSid.setText(newValue.getSid());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
        txtIid.setText(newValue.getIid());
        txtItemName.setText(newValue.getItemName());
        txtPric.setText(String.valueOf(newValue.getPrice()));
        txtQuantity.setText(String.valueOf(newValue.getQuantity()));

    }

    private void loadStockData(String SearchID) {
        ObservableList<SupplierItemDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<SupplierItemDTO> otherItemData = supplierItemCrudDAODAO.getData();
            for (SupplierItemDTO s : otherItemData) {
                if (s.getName().contains(SearchID) || s.getSid().contains(SearchID)) {
                    SupplierItemDTO oti = new SupplierItemDTO(s.getSid(), s.getName(), s.getAddress(),s.getContact(), s.getIid(), s.getItemName(), s.getPrice(), s.getQuantity());
                    list.add(oti);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblSupplierItem.setItems(list);
    }


    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Sid = txtSid.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String Iid = txtIid.getText();
        String ItemName = txtItemName.getText();
        double price = Double.parseDouble(txtPric.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());


        try {
            SupplierItemDTO supplierItemDTO = new SupplierItemDTO(Sid, name, address, contact,Iid, ItemName, price, quantity);
            boolean isInserted = supplierItemCrudDAODAO.insertSupplierTransaction(supplierItemDTO);
            if (isInserted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong Supplier !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not Added !\n" + e).show();
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
        }
        loadStockData("");
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


    public void NewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String nextID = generateNextStockItemID(supplierCrudDAO.getID());
        txtSid.setText(nextID);
    }


    public void supplierDOnAction(ActionEvent actionEvent) {
      /*  try {
            ResultSet set = SupplierItemModel.getSuppdetailss(String.valueOf(combSupplierD.getValue()));
            if (set.next()) {
                txtSid.setText(set.getString(1));
                txtName.setText(set.getString(2));
                txtAddress.setText(set.getString(3));
                txtContact.setText(set.getString(4));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }*/
    }

    public void btnIidOnActionn(ActionEvent actionEvent) {
        try {
            ResultSet set = (ResultSet) itemCrudDAO.search(String.valueOf(combIiDD.getValue()));
            if (set.next()) {
                txtIid.setText(set.getString(1));
                txtItemName.setText(set.getString(2));
                txtPric.setText(set.getString(3));
                txtQuantity.setText(set.getString(4));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
    private void setRegIdS() {
        try {
            ArrayList<String> codes = supplierItemCrudDAODAO.loadIteCodes();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String code : codes) {
                obList.add(code);
            }
            combIiDD.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
 /*   private void setRegIdSss() {
        try {
            ArrayList<String> codes = SupplierItemModel.loadSupplierCodes();
            ObservableList<String> obList = FXCollections.observableArrayList();

            for (String code : codes) {
                obList.add(code);
            }
            combSupplierD.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/


}
