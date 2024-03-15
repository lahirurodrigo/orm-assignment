package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Book;

public interface BookDAO extends SuperDAO {
    boolean save(Book book);

    boolean update(Book book);

    Book search(String id);

    boolean delete(Book book);

    String generateNextId() throws Exception;
}
