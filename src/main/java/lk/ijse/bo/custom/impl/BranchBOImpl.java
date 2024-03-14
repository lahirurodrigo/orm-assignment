package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchDTO;

public class BranchBOImpl implements BranchBO {
    @Override
    public boolean saveBranch(BranchDTO branchDTO) {
        return false;
    }

    @Override
    public boolean updateBranch(BranchDTO branchDTO) {
        return false;
    }

    @Override
    public BranchDTO searchBranch(String id) {
        return null;
    }

    @Override
    public boolean deleteBranch(String id) {
        return false;
    }
}
