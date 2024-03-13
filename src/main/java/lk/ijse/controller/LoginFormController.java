package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.dto.AdminDTO;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label btnSigninPage;

    @FXML
    private JFXComboBox<?> cmbType;

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

    LoginBO loginBO = new LoginBOImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        AdminDTO adminDTO = loginBO.checkCredentials(username);

        /*  Uncomment this after connecting the database */

        /*if (!(adminDTO.getPassword().equals(password))){
            new Alert(Alert.AlertType.ERROR,"password incorrect").showAndWait();
            return;
        }*/

        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.rootVary.getChildren().clear();
        this.rootVary.getChildren().add(rootNew);
    }

    @FXML
    void btnSigninPageOnAction(MouseEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        this.rootVary.getChildren().clear();
        this.rootVary.getChildren().add(rootNew);
    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {
        btnLogin.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        cmbType.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
