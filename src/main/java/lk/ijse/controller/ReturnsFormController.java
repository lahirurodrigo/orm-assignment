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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.bo.custom.ReturnBO;
import lk.ijse.bo.custom.impl.BorrowalBOImpl;
import lk.ijse.bo.custom.impl.ReturnBOImpl;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BorrowalDAO;
import lk.ijse.dao.custom.MemberDAO;
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
    private Label lblB1;

    @FXML
    private Label lblB2;

    @FXML
    private Label lblID;

    @FXML
    private Label lblM;

    @FXML
    private JFXComboBox<String> cmbBorrowalId;

    @FXML
    private DatePicker dtpDue;

    @FXML
    private DatePicker dtpReturnDate;

    ReturnBO returnBO = (ReturnBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RETURN);

    BorrowalDAO borrowalDAO = (BorrowalDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.BORROW);

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.QUERY);

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
            new Alert(Alert.AlertType.CONFIRMATION,"Return recorded successfully!").showAndWait();

        }catch (Exception e){
            transaction.rollback();
            new Alert(Alert.AlertType.ERROR,"Return recording unsuccessful").showAndWait();
        }finally {
            session.close();
            clearFields();
        }
    }

    private void clearFields() {
        Parent rootNew = null;
        try {
            rootNew = FXMLLoader.load(getClass().getResource("/view/returns_form.fxml"));
            this.rootNode.getChildren().clear();
            this.rootNode.getChildren().add(rootNew);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String borrowalId = cmbBorrowalId.getValue();

        boolean isDelete = returnBO.deleteReturn(borrowalId);
    }

    @FXML
    void btnOverdueOnAction(ActionEvent event) throws IOException {
        Parent rootNew = FXMLLoader.load(getClass().getResource("/view/overdues_form.fxml"));
        Scene scene = new Scene(rootNew);
        Stage stage = new Stage();
        stage.setTitle("BookWorm");
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
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
        Borrowals borrowal = borrowalDAO.search(cmbBorrowalId.getValue());
        dtpDue.setValue(borrowal.getDue_date());
        lblM.setText(borrowal.getMember().getId()+"  "+borrowal.getMember().getName());
        lblB1.setText(borrowal.getBooks().get(0).getTitle());

        if (borrowal.getBooks().size()==2){
        lblB2.setText(borrowal.getBooks().get(1).getTitle());
        }
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
