package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;

import java.util.List;

public interface BranchDAO extends SuperDAO {
    String genatareNextId()throws Exception;

    List<Branch> getAll();

    boolean save(Branch branch);

    boolean update(Branch branch);

    Branch search(String id);

    boolean delete(Branch branch);
}
