package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.BorrowalDAO;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BorrowalDAOImpl;
import lk.ijse.dao.custom.impl.MemberDAOImpl;
import lk.ijse.dto.BorrowalDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class BorrowalBOImpl implements BorrowalBO {

    BorrowalDAO borrowalDAO = new BorrowalDAOImpl();

    BookDAO bookDAO = new BookDAOImpl();

    MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean addBorrowal(BorrowalDTO borrowalDTO) {

        Member member = memberDAO.search(borrowalDTO.getMemberId());

        List<Book> books = new ArrayList<>();

        books.add(bookDAO.search(borrowalDTO.getBook1Id()));

        if (!(borrowalDTO.getBook2Id().equals(null))){
            books.add(bookDAO.search(borrowalDTO.getBook2Id()));
        }

        return borrowalDAO.save(new Borrowals(
                borrowalDTO.getId(),
                borrowalDTO.getDate(),
                borrowalDTO.getDuedate(),
                member,
                books
        ));
    }

    @Override
    public boolean deleteBorrowal(BorrowalDTO borrowalDTO) {
        Member member = memberDAO.search(borrowalDTO.getMemberId());

        List<Book> books = new ArrayList<>();

        books.add(bookDAO.search(borrowalDTO.getBook1Id()));

        if (!(borrowalDTO.getBook2Id().equals(null))){
            books.add(bookDAO.search(borrowalDTO.getBook2Id()));
        }

        return borrowalDAO.delete(new Borrowals(
                borrowalDTO.getId(),
                borrowalDTO.getDate(),
                borrowalDTO.getDuedate(),
                member,
                books
        ));
    }

    @Override
    public boolean updateBorrowal(BorrowalDTO borrowalDTO) {
        Member member = memberDAO.search(borrowalDTO.getMemberId());

        List<Book> books = new ArrayList<>();

        books.add(bookDAO.search(borrowalDTO.getBook1Id()));

        if (!(borrowalDTO.getBook2Id().equals(null))){
            books.add(bookDAO.search(borrowalDTO.getBook2Id()));
        }

        return borrowalDAO.update(new Borrowals(
                borrowalDTO.getId(),
                borrowalDTO.getDate(),
                borrowalDTO.getDuedate(),
                member,
                books
        ));
    }

    @Override
    public BorrowalDTO searchBorrowal(String id) {
        Borrowals borrowals = borrowalDAO.search(id);

        List<Book> books = borrowals.getBooks();

        if(books.size()==1){
            books.add(new Book());
        }

        return new BorrowalDTO(
                borrowals.getId(),
                borrowals.getMember().getId(),
                borrowals.getBooks().get(1).getId(),
                borrowals.getBooks().get(2).getId(),
                borrowals.getBorrow_date(),
                borrowals.getDue_date()
        );

    }

    @Override
    public String generateNextBorrowalId() throws Exception {
        return borrowalDAO.generateNextId();
    }

    @Override
    public List<String> loadAllMembers() {
        List<Member> list = memberDAO.getAll();
        List<String> ids = new ArrayList<>();

        for ( Member member : list){
            ids.add(member.getId());
        }

        return ids;
    }

    @Override
    public List<String> loadAllBooks() {
        List<Book> list = bookDAO.getAll();
        List<String> ids = new ArrayList<>();

        for ( Book book : list){
            ids.add(book.getId());
        }

        return ids;
    }

    @Override
    public List<String> loadAvailableBooks() {
        List<Book> list = bookDAO.getAvailableBooks();
        List<String> ids = new ArrayList<>();

        for ( Book book : list){
            ids.add(book.getId());
        }

        return ids;
    }
}
