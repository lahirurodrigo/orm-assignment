package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.MemberBO;
import lk.ijse.bo.custom.impl.MemberBOImpl;
import lk.ijse.dto.MemberDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MemberFormController implements Initializable {


    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnMemberActivities;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    MemberBO memberBO = new MemberBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextMemberId();
    }

    private void generateNextMemberId() {
        try {
            txtID.setText(memberBO.generateNextMemberID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        String email = txtEmail.getText();

        MemberDTO memberDTO = new MemberDTO(id,name,email);

        boolean isSaved = memberBO.saveMember(memberDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Member added Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Member adding unsuccessful").showAndWait();
        }


    }

    private void clearFields() {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();

        boolean isDelete = memberBO.deleteMember(new MemberDTO(id,name,email));

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Member deleted Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Member deleting unsuccessful").showAndWait();
        }
    }

    @FXML
    void btnMemberActivitiesOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();

        MemberDTO memberDTO = new MemberDTO(id,name,email);

        boolean isUpdate = memberBO.updateMember(memberDTO);

        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Member updated Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Member updating unsuccessful").showAndWait();
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

        String id = txtID.getText();

        MemberDTO memberDTO = memberBO.searchMember(id);

        txtName.setText(memberDTO.getUsername());
        txtEmail.setText(memberDTO.getEmail());

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

}
