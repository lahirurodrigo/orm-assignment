package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;
import lk.ijse.bo.custom.impl.LoginBOImpl;
import lk.ijse.dto.AdminDTO;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSigninPage;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private AnchorPane rootVary;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"fields are empty").show();
            return;
        }

        boolean isMatch = false;
        try {
            isMatch = loginBO.checkCredentials(username,password);
            if (!(isMatch)){
                new Alert(Alert.AlertType.ERROR,"check username and password").showAndWait();
                return;
            }

            Parent rootNew = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
            this.rootVary.getChildren().clear();
            this.rootVary.getChildren().add(rootNew);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSigninPageOnAction(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/signin_form.fxml"));
        this.rootVary.getChildren().clear();
        this.rootVary.getChildren().add(rootNew);
    }


    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnLogin.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
