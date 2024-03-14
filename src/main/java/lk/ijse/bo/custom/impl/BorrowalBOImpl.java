package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BorrowalBO;
import lk.ijse.dto.BorrowalDTO;

public class BorrowalBOImpl implements BorrowalBO {
    @Override
    public boolean addBorrowal(BorrowalDTO borrowalDTO) {
        return false;
    }

    @Override
    public boolean deleteBorrowal(String id) {
        return false;
    }

    @Override
    public boolean updateBorrowal(BorrowalDTO borrowalDTO) {
        return false;
    }

    @Override
    public BorrowalDTO searchBorrowal(String id) {
        return null;
    }
}
