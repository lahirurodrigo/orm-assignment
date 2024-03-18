package lk.ijse.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.OverdueBO;
import lk.ijse.bo.custom.impl.OverdueBOImpl;
import lk.ijse.dto.tm.OverdueTM;

import java.net.URL;
import java.util.ResourceBundle;

public class OverduesFormController implements Initializable {


    @FXML
    private TableColumn<?, ?> colBook1Id;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private TableColumn<?, ?> colBorrowal;

    @FXML
    private TableView<OverdueTM> tblTimeOut;

    OverdueBO overdueBO = (OverdueBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.OVERDUE);;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        setOverdueDetails();
    }

    private void setCellValues() {
        colBorrowal.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("memberid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBook1Id.setCellValueFactory(new PropertyValueFactory<>("book1Id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book2Id"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
    }

    private void setOverdueDetails() {
        ObservableList<OverdueTM> overdueTMs = overdueBO.getAllOverdues();
        tblTimeOut.setItems(overdueTMs);

    }



    @FXML
    void mouseClickOnAction(MouseEvent event) {

    }
}
