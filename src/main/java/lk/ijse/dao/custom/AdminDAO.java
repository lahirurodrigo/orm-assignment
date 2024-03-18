package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.MemberDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;

import java.util.List;

public interface AdminDAO extends SuperDAO {
    boolean save(Admin adminDTO);

    String generateNextId() throws Exception;

    public boolean check(Admin admin)throws Exception;

    List<Admin> getAll();

    Admin search(String adminId);

    boolean updateAdmin(Admin admin);

    List<Branch> getBranchList(String id);
}
