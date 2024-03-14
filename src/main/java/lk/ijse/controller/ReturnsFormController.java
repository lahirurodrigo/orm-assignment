package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import lk.ijse.bo.custom.ReturnBO;
import lk.ijse.bo.custom.impl.ReturnBOImpl;
import lk.ijse.dto.ReturnDTO;

import java.time.LocalDate;

public class ReturnsFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnOverdue;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXComboBox<String> cmbBorrowalId;

    @FXML
    private DatePicker dtpDue;

    @FXML
    private DatePicker dtpReturnDate;

    ReturnBO returnBO = new ReturnBOImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String borrowalId = cmbBorrowalId.getValue();
        LocalDate dueDate = dtpDue.getValue();
        LocalDate returnDate = dtpReturnDate.getValue();

        ReturnDTO returnDTO = new ReturnDTO(borrowalId,dueDate,returnDate);

        boolean isAdd = returnBO.addReturn(returnDTO);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String borrowalId = cmbBorrowalId.getValue();

        boolean isDelete = returnBO.deleteReturn(borrowalId);
    }

    @FXML
    void btnOverdueOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String borrowalId = cmbBorrowalId.getValue();
        LocalDate dueDate = dtpDue.getValue();
        LocalDate returnDate = dtpReturnDate.getValue();

        ReturnDTO returnDTO = new ReturnDTO(borrowalId,dueDate,returnDate);

        boolean isUpdate = returnBO.updateReturn(returnDTO);
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        String borrowalId = cmbBorrowalId.getValue();

        ReturnDTO returnDTO = returnBO.searchReturn(borrowalId);
    }

    @FXML
    void cmbBorrowalIdOnAction(ActionEvent event) {
        dtpDue.requestFocus();
    }

    @FXML
    void dtpDueOnAction(ActionEvent event) {
        dtpReturnDate.requestFocus();
    }

    @FXML
    void dtpReturnDateOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

}