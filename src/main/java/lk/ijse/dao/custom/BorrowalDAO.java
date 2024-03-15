package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;

public interface BorrowalDAO extends SuperDAO {
    boolean save(Borrowals borrowal);

    boolean update(Borrowals borrowals);

    Borrowals search(String id);

    boolean delete(Borrowals borrowals);

    String generateNextId() throws Exception;
}
