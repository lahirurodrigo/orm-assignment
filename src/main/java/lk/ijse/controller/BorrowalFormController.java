package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.bo.custom.impl.BorrowalBOImpl;
import lk.ijse.dto.BorrowalDTO;

import java.time.LocalDate;

public class BorrowalFormController {

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
    private JFXComboBox<String> cmbBook1;

    @FXML
    private JFXComboBox<String> cmbBook2;

    @FXML
    private JFXComboBox<String> cmbMember;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private DatePicker dtpDue;

    @FXML
    private JFXTextField txtID;

    BorrowalBO borrowalBO = new BorrowalBOImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String memberId = cmbMember.getValue();
        String book1Id = cmbBook1.getValue();
        String book2Id = cmbBook2.getValue();
        LocalDate date = dtpDate.getValue();
        LocalDate duedate = dtpDue.getValue();

        BorrowalDTO borrowalDTO = new BorrowalDTO(id,memberId,book1Id,book2Id,date,duedate);

        boolean isAdd = borrowalBO.addBorrowal(borrowalDTO);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        boolean isDelete = borrowalBO.deleteBorrowal(id);
    }

    @FXML
    void btnOverdueOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String memberId = cmbMember.getValue();
        String book1Id = cmbBook1.getValue();
        String book2Id = cmbBook2.getValue();
        LocalDate date = dtpDate.getValue();
        LocalDate duedate = dtpDue.getValue();

        BorrowalDTO borrowalDTO = new BorrowalDTO(id,memberId,book1Id,book2Id,date,duedate);

        boolean isAdd = borrowalBO.updateBorrowal(borrowalDTO);
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        String id = txtID.getText();

        BorrowalDTO borrowalDTO = borrowalBO.searchBorrowal(id);
    }

    @FXML
    void cmbBook1OnAction(ActionEvent event) {
        cmbBook2.requestFocus();
    }

    @FXML
    void cmbBook2OnAction(ActionEvent event) {
        dtpDate.requestFocus();
    }

    @FXML
    void cmbMemberOnAction(ActionEvent event) {
        cmbBook1.requestFocus();
    }

    @FXML
    void dtpDateOnAction(ActionEvent event) {
        dtpDue.requestFocus();
    }

    @FXML
    void dtpDueOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        cmbMember.requestFocus();
    }

}
