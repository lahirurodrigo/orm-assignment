package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.custom.ActivitiesBO;
import lk.ijse.bo.custom.impl.ActivitiesBOImpl;
import lk.ijse.dto.tm.ActivitiesTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ActivitiesFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<ActivitiesTM> tblOverdues;

    ActivitiesBO activitiesBO = new ActivitiesBOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValues();
        setMemberDetails();
    }

    private void setCellValues() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    private void setMemberDetails() {

        ObservableList<ActivitiesTM> obList = FXCollections.observableArrayList();

        List<ActivitiesTM> list = activitiesBO.getOverdueMembers();

        for (ActivitiesTM tm : list){
            System.out.println(tm.getId()+"/n");
            obList.add(tm);
        }

        tblOverdues.setItems(obList);

    }

}
