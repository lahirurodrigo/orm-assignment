package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDTO;

public interface BranchBO extends SuperBO {
    boolean saveBranch(BranchDTO branchDTO);

    boolean updateBranch(BranchDTO branchDTO);

    BranchDTO searchBranch(String id);

    boolean deleteBranch(String id);
}
