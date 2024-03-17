package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Borrowals;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> loadDueBorrowals();

    List<Borrowals> getAllOverdues();
}
