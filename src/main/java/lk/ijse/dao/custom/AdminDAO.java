package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Admin;

public interface AdminDAO extends SuperDAO {
    boolean save(Admin adminDTO);

    String generateNextId() throws Exception;

    public boolean check(Admin admin)throws Exception;
}
