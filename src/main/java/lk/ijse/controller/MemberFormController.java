package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.MemberBO;
import lk.ijse.bo.custom.impl.MemberBOImpl;
import lk.ijse.dto.MemberDTO;

import java.io.IOException;

public class MemberFormController {


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


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        boolean isDelete = memberBO.deleteMember(id);
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

        boolean isUpdate = memberBO.updateMember(memberBO);
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

        String id = txtID.getText();

        MemberDTO memberDTO = memberBO.searchMember(id);

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
