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
import lk.ijse.bo.BOFactory;
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
    private AnchorPane rootNode;

    @FXML
    private AnchorPane rootVary;

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    SigninBO signinBO = (SigninBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGNIN);

    @FXML
    void btnLoginPageOnAction(ActionEvent event) throws IOException {

        Parent rootNew = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNew);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextAdminId();
    }

    private void generateNextAdminId() {
        try{

            String id = signinBO.genarateNextAdminId();
            lblID.setText(id);

        }catch (Exception e){

        }
    }


    @FXML
    void btnSigninOnAction(ActionEvent event) throws IOException {
        String id = lblID.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        boolean valid = validateUser(username, password);

        if(!valid){
            return;
        }

            AdminDTO adminDTO = new AdminDTO(id,username, password);

            boolean isSaved = signinBO.saveAdmin(adminDTO);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Account created Successfully!").showAndWait();
                Parent rootNew = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
                this.rootNode.getChildren().clear();
                this.rootNode.getChildren().add(rootNew);
            }else{
                new Alert(Alert.AlertType.ERROR,"Account creation unsuccessful").showAndWait();
            }


    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignin.requestFocus();
    }

    @FXML
    void txtUsernameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }


    private boolean validateUser(String name,String password){

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




        return true;
    }

}