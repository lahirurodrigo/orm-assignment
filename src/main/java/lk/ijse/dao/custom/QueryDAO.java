package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Member;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> loadDueBorrowals();

    List<Borrowals> getAllOverdues();

    List<Member> getValidMembers();

    List<Member> getOverdueMembers();
}
