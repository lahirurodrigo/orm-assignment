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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.bo.custom.impl.BorrowalBOImpl;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.MemberDAOImpl;
import lk.ijse.dto.BorrowalDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowalFormController implements Initializable {


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
    private JFXComboBox<String> cmbBook1;

    @FXML
    private JFXComboBox<String> cmbBook2;

    @FXML
    private JFXComboBox<String> cmbMember;


    @FXML
    private Label lblB1Name;

    @FXML
    private Label lblB2Name;

    @FXML
    private Label lblMName;

    @FXML
    private DatePicker dtpDate;

    @FXML
    private DatePicker dtpDue;

    @FXML
    private JFXTextField txtID;

    BorrowalBO borrowalBO = new BorrowalBOImpl();

    MemberDAO memberDAO = new MemberDAOImpl();

    BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextBorrowalId();
        loadAllMembers();
        loadAvailableBooks();
    }

    private void loadAllMembers() {
        List<String> membersIds = borrowalBO.loadAllMembers();

        ObservableList<String> m_ids = FXCollections.observableArrayList();

        for(String id : membersIds){
            m_ids.add(id);
        }

        cmbMember.setItems(m_ids);
    }

    private void loadAvailableBooks() {
        List<String> bookIds = borrowalBO.loadAvailableBooks();

        ObservableList<String> b_ids = FXCollections.observableArrayList();

        for(String id : bookIds){
            b_ids.add(id);
        }

        cmbBook1.setItems(b_ids);
        cmbBook2.setItems(b_ids);
    }

    private void generateNextBorrowalId() {
        try {
            txtID.setText(borrowalBO.generateNextBorrowalId());
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
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        String memberId = cmbMember.getValue();
        String book1Id = cmbBook1.getValue();
        String book2Id = cmbBook2.getValue();
        LocalDate date = dtpDate.getValue();
        LocalDate duedate = dtpDue.getValue();

        BorrowalDTO borrowalDTO = new BorrowalDTO(id,memberId,book1Id,book2Id,date,duedate);

        boolean isAdd = borrowalBO.deleteBorrowal(borrowalDTO);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();
        String memberId = cmbMember.getValue();
        String book1Id = cmbBook1.getValue();
        String book2Id = cmbBook2.getValue();
        LocalDate date = dtpDate.getValue();
        LocalDate duedate = dtpDue.getValue();

        Member member = memberDAO.search(memberId);

        List<Book> books = new ArrayList<>();

        books.add(bookDAO.search(book1Id));

        if (!(book2Id.equals(null))){
            books.add(bookDAO.search(book2Id));
        }

        Borrowals borrowal = new Borrowals(id,date,duedate,member,books);

        Session session = null;
        Transaction transaction = null;


        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            session.save(borrowal);

            for (Book book : books){

                int qua = (book.getQuantity()-1);
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

        cmbMember.setValue(borrowalDTO.getMemberId());
        cmbBook1.setValue(borrowalDTO.getBook1Id());
        cmbBook2.setValue(borrowalDTO.getBook2Id());
        dtpDate.setValue(borrowalDTO.getDate());
        dtpDue.setValue(borrowalDTO.getDuedate());
    }

    @FXML
    void cmbBook1OnAction(ActionEvent event) {

        lblB1Name.setText(bookDAO.search(cmbBook1.getValue()).getTitle());
        cmbBook2.requestFocus();
    }

    @FXML
    void cmbBook2OnAction(ActionEvent event) {
        lblB2Name.setText(bookDAO.search(cmbBook2.getValue()).getTitle());
        dtpDue.requestFocus();

    }

    @FXML
    void cmbMemberOnAction(ActionEvent event) {

        lblMName.setText(memberDAO.search(cmbMember.getValue()).getName());
        cmbBook1.requestFocus();
    }

    @FXML
    void dtpDateOnAction(ActionEvent event) {
        cmbMember.requestFocus();
    }

    @FXML
    void dtpDueOnAction(ActionEvent event) {
        btnAdd.requestFocus();
    }

    @FXML
    void txtIDOnAction(ActionEvent event) {
        dtpDate.requestFocus();
    }

}
