package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.bo.custom.impl.BranchBOImpl;
import lk.ijse.dto.BranchDTO;

import java.io.IOException;
import java.util.regex.Pattern;

public class BranchFormController {


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
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    BranchBO branchBO = new BranchBOImpl();

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

        if(validateUser(name)){
            boolean isSaved = branchBO.saveBranch(new BranchDTO(id,name));

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Branch added Successfully!").showAndWait();
            }else{
                new Alert(Alert.AlertType.ERROR,"Branch adding unsuccessful").showAndWait();
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        boolean isDelete = branchBO.deleteBranch(id);


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();

        if(validateUser(name)){
            boolean isUpdated = branchBO.updateBranch(new BranchDTO(id,name));

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Branch updated Successfully!").showAndWait();
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
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
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
