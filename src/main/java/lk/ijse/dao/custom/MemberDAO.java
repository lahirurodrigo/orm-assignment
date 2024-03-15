package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Member;

public interface MemberDAO extends SuperDAO {
    boolean save(Member member);

    Member search(String id);

    boolean update(Member member);

    boolean delete(Member member);

    String generateNextId() throws Exception;
}
