package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.regex.Pattern;

public class SigninFormController {

    @FXML
    private Label btnLoginPage;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    void btnLoginPageOnAction(MouseEvent event) {

    }

    @FXML
    void btnSigninOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String type = cmbType.getValue();

        if (txtPassword.getText()==null || txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"password field cannot be empty").showAndWait();
            return;
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

    private boolean validateUser(String name,String password, String email){

        boolean matches1 = Pattern.matches("[A-Za-z\\s]{3,}",name);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer name").showAndWait();
            return false;
        }

        boolean matches2 = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",password);
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Invalid password. \n should contain minimum eight characters, at least one letter and one number:").showAndWait();
            return false;
        }

        boolean matches3 = Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",email);
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Invalid Customer email address").showAndWait();
            return false;
        }

        return true;
    }

}