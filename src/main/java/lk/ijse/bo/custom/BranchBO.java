package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;

import java.util.List;

public interface BranchBO extends SuperBO {
    boolean saveBranch(BranchDTO branchDTO);

    boolean updateBranch(BranchDTO branchDTO);

    BranchDTO searchBranch(String id);

    boolean deleteBranch(BranchDTO branchDTO);

    String generateNextBranchID() throws Exception;

    List<BranchDTO> getAllBranches();

    List<String> getAllAdmins();
}
