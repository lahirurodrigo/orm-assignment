package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.dto.BranchDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BranchFormController implements Initializable {


    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnDashboard;


    @FXML
    private JFXComboBox<String> cmbAdminID;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllAdminIds();
        generateNextBranchID();
    }


    private void generateNextBranchID() {
        String id  = null;
        try {
            id = branchBO.generateNextBranchID();
            txtID.setText(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void loadAllAdminIds() {
        List<String> admins = branchBO.getAllAdmins();
        ObservableList<String> adminIds = FXCollections.observableArrayList();

        for (String id : admins) {
            adminIds.add(id);
        }

        cmbAdminID.setItems(adminIds);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNew);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String admin_id = cmbAdminID.getValue();

        System.out.println(admin_id);


        if(validateUser(name)){
            boolean isSaved = branchBO.saveBranch(new BranchDTO(id,name,admin_id));

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Branch added Successfully!").showAndWait();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Branch adding unsuccessful").showAndWait();
            }
        }
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        cmbAdminID.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String admin_id = cmbAdminID.getValue();


        boolean isDelete = branchBO.deleteBranch(new BranchDTO(id,name,admin_id));

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Branch deleted Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Remove the Books").showAndWait();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String admin_id = cmbAdminID.getValue();

        if(validateUser(name)){
            boolean isUpdated = branchBO.updateBranch(new BranchDTO(id,name,admin_id));

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Branch updated Successfully!").showAndWait();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Branch updating unsuccessful").showAndWait();
            }
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        String id = txtID.getText();

        BranchDTO branchDTO = branchBO.searchBranch(id);

        txtName.setText(branchDTO.getName());
        cmbAdminID.setValue(branchDTO.getAdmin_id());
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        cmbAdminID.requestFocus();
    }

    @FXML
    void cmbAdminIDOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

    private boolean validateUser(String name){

        boolean matches1 = Pattern.matches("[A-Za-z\\s]{3,}",name);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Give a valid name").showAndWait();
            return false;
        }

        return true;
    }
}
