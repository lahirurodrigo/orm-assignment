package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.SigninBO;
import lk.ijse.bo.custom.impl.SigninBOImpl;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.MemberDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SigninFormController implements Initializable {

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXButton btnLoginPage;

    @FXML
    private JFXComboBox<String> cmbType;


    @FXML
    private AnchorPane rootNode;

    @FXML
    private AnchorPane rootVary;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    SigninBO signinBO = new SigninBOImpl();

    @FXML
    void btnLoginPageOnAction(ActionEvent event) throws IOException {

        System.out.println("hi");
        Parent rootNew = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNew);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDataToCMB();
    }

    private void addDataToCMB() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Admin");
        obList.add("User");
        cmbType.setItems(obList);
    }

    @FXML
    void btnSigninOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String type = cmbType.getValue();

        boolean valid = validateUser(username, password, email, type);

        if(!valid){
            return;
        }

        if (type.equals("Admin")){
            AdminDTO adminDTO = new AdminDTO(username, password, email);

            boolean saved = signinBO.saveAdmin(adminDTO);

        }else{
            MemberDTO memberDTO = new MemberDTO(username, password, email);

            boolean saved = signinBO.saveMember(memberDTO);

        }

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        cmbType.requestFocus();
    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {
        btnSignin.requestFocus();
    }

    private boolean validateUser(String name,String password, String email, String type){

        boolean matches1 = Pattern.matches("[A-Za-z\\s]{3,}",name);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer name").showAndWait();
            return false;
        }

        boolean matches2 = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",password);
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid password. \nshould contain minimum eight characters, at least one letter and one number:").showAndWait();
            return false;
        }

        boolean matches3 = Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",email);
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer email address").showAndWait();
            return false;
        }

        if (cmbType.getValue() == null || cmbType.getValue().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"select a type").showAndWait();
            return false;
        }

        return true;
    }

}