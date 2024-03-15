package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.bo.custom.ReturnBO;
import lk.ijse.bo.custom.impl.BorrowalBOImpl;
import lk.ijse.bo.custom.impl.ReturnBOImpl;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BorrowalDAO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.BorrowalDAOImpl;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.ReturnDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Returns;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnsFormController implements Initializable {


    @FXML
    private AnchorPane rootNode;

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
    private JFXButton btnDashboard;

    @FXML
    private Label lblID;

    @FXML
    private JFXComboBox<String> cmbBorrowalId;

    @FXML
    private DatePicker dtpDue;

    @FXML
    private DatePicker dtpReturnDate;

    ReturnBO returnBO = new ReturnBOImpl();

    BorrowalDAO borrowalDAO = new BorrowalDAOImpl();

    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDueBorrowals();
        generateNextReturnsId();
    }

    private void generateNextReturnsId() {
        try {
            lblID.setText(returnBO.generateNextReturnID());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDueBorrowals() {
        List<Object[]> borrowals = queryDAO.loadDueBorrowals();

        ObservableList<String> br_ids = FXCollections.observableArrayList();

        for(Object[] borrow : borrowals){
            br_ids.add(borrow[0].toString());
        }

        cmbBorrowalId.setItems(br_ids);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String id = lblID.getText();
        String borrowalId = cmbBorrowalId.getValue();
        LocalDate dueDate = dtpDue.getValue();
        LocalDate returnDate = dtpReturnDate.getValue();

        Borrowals borrowal = borrowalDAO.search(borrowalId);

        List<Book> books = borrowal.getBooks();

        Returns returns = new Returns(
                id,
                borrowal,
                dueDate,
                returnDate
        );



        Session session = null;
        Transaction transaction = null;


        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.save(returns);

            for (Book book : books){

                int qua = (book.getQuantity()+1);
                System.out.println(book.getQuantity());
                System.out.println(qua);

                Book bookN = new Book(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        qua,
                        book.getBranch()
                );
                session.update(bookN);
            }

            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }
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
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        this.rootNode.getChildren().clear();
        this.rootNode.getChildren().add(rootNew);
    }

    @FXML
    void cmbBorrowalIdOnAction(ActionEvent event) {
        dtpDue.setValue(borrowalDAO.search(cmbBorrowalId.getValue()).getDue_date());
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
