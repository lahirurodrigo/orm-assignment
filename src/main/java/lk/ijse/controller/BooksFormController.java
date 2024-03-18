package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.dto.BookDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BooksFormController implements Initializable {


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
    private JFXButton btnBookList;

    @FXML
    private JFXButton btnDashboard;

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

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextBookId();
        loadAllBranchIds();
        loadStatus();
    }

    private void loadStatus() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("available");
        obList.add("unavailable");

        cmbStatus.setItems(obList);
    }

    private void loadAllBranchIds() {
        List<String> ids = bookBO.loadAllBranchIds();

        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String id : ids){
            obList.add(id);
        }

        cmbBranch.setItems(obList);
    }

    private void generateNextBookId() {
        try {
            txtID.setText(bookBO.generateNextBookID());
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

        if(cmbStatus.getValue().isBlank()||cmbStatus.getValue().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Select status").showAndWait();
        }

        String id = txtID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        String branch = cmbBranch.getValue();
        String status = cmbStatus.getValue();

        boolean isSaved = bookBO.saveBook(new BookDTO(id, title, author, genre, quantity, branch, status));

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"Book added Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Book adding unsuccessful").showAndWait();
        }
    }

    private void clearFields() {
        txtID.clear();
        txtTitle.clear();
        txtAuthor.clear();
        txtGenre.clear();
        txtQuantity.clear();
        cmbBranch.getSelectionModel().clearSelection();
        cmbStatus.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String id = txtID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        String branch = cmbBranch.getValue();
        String status = cmbStatus.getValue();

        boolean isDelete = bookBO.deleteBook(new BookDTO(id, title, author, genre, quantity, branch, status));

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION,"Book deleted Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Book deleting unsuccessful").showAndWait();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = txtID.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        String branch = cmbBranch.getValue();
        String status = cmbStatus.getValue();

        boolean isUpdate = bookBO.updateBook(new BookDTO(id, title, author, genre, quantity, branch, status));

        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Book updated Successfully!").showAndWait();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,"Book updating unsuccessful").showAndWait();
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {

        String id = txtID.getText();

        BookDTO bookDTO = bookBO.searchBook(id);

        txtTitle.setText(bookDTO.getTitle());
        txtAuthor.setText(bookDTO.getAuthor());
        txtGenre.setText(bookDTO.getGenre());
        txtQuantity.setText(String.valueOf(bookDTO.getQuantity()));
        cmbBranch.setValue(bookDTO.getBranch());
        cmbStatus.setValue(bookDTO.getAvailability());
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
