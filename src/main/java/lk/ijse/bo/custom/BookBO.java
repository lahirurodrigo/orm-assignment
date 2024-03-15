package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;

import java.util.List;

public interface BookBO extends SuperBO {

    boolean saveBook(BookDTO bookDTO);

    boolean updateBook(BookDTO bookDTO);

    BookDTO searchBook(String id);

    boolean deleteBook(BookDTO bookDTO);

    String generateNextBookID() throws Exception;

    List<String> loadAllBranchIds();
}
