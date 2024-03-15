package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    BranchDAO branchDAO = new BranchDAOImpl();

    BookDAO bookDAO = new BookDAOImpl();
    @Override
    public boolean saveBook(BookDTO bookDTO) {
        Branch branch = branchDAO.search(bookDTO.getBranch());

        return bookDAO.save(new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getGenre(),
                bookDTO.getQuantity(),
                branch
        ));

    }

    @Override
    public boolean updateBook(BookDTO bookDTO) {

        Branch branch = branchDAO.search(bookDTO.getBranch());

        return bookDAO.update(new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getGenre(),
                bookDTO.getQuantity(),
                branch
        ));
    }

    @Override
    public BookDTO searchBook(String id) {
        Book book = bookDAO.search(id);

        String availability;

        if (book.getQuantity()>0){
            availability="available";
        }else {
            availability = "unavailable";
        }

        return new BookDTO(
          book.getId(),
          book.getTitle(),
          book.getAuthor(),
          book.getGenre(),
          book.getQuantity(),
          book.getBranch().getId(),
          availability
        );
    }

    @Override
    public boolean deleteBook(BookDTO bookDTO) {
        Branch branch = branchDAO.search(bookDTO.getBranch());

        return bookDAO.delete(new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getGenre(),
                bookDTO.getQuantity(),
                branch
        ));
    }

    @Override
    public String generateNextBookID() throws Exception {
        return bookDAO.generateNextId();
    }

    @Override
    public List<String> loadAllBranchIds() {
        List<Branch> branches = branchDAO.getAll();

        List<String> ids = new ArrayList<>();

        for (Branch branch : branches){
            ids.add(branch.getId());
        }

        return ids;
    }
}
