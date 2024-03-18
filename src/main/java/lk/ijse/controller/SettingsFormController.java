package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dto.MemberDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btndelete;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ADMIN);

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNew);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();

        List<Branch> branches = adminDAO.getBranchList(id);

        Admin admin = new Admin(id,name,email,branches);

        boolean isUpdate = adminDAO.updateAdmin(admin);

        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Member updated Successfully!").showAndWait();
        }else{
            new Alert(Alert.AlertType.ERROR,"Member updating unsuccessful").showAndWait();
        }
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtIDOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

}