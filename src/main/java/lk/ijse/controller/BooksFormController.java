package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BooksFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnBookList;

    @FXML
    private JFXComboBox<String> cmbBranch;

    @FXML
    private JFXComboBox<String> cmbStatus;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtGenre;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String id = txtID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int Quantity = Integer.parseInt(txtQuantity.getText());
        String Branch = cmbBranch.getValue();
        String status = cmbStatus.getValue();




    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

    }

    @FXML
    void cmbBranchOnAction(ActionEvent event) {
        cmbStatus.requestFocus();
    }

    @FXML
    void cmbStatusOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

    @FXML
    void txtAuthorOnAction(ActionEvent event) {
        txtQuantity.requestFocus();
    }

    @FXML
    void txtGenreOnAction(ActionEvent event) {
        cmbBranch.requestFocus();
    }

    @FXML
    void txtQuantityOnAction(ActionEvent event) {
        txtGenre.requestFocus();
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        txtTitle.requestFocus();
    }

    @FXML
    void txtTitleOnAction(ActionEvent event) {
        txtAuthor.requestFocus();
    }

    @FXML
    void btnBookListOnAction(ActionEvent event) {

    }

}
