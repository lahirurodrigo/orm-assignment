package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReturnBO;
import lk.ijse.dto.ReturnDTO;

public class ReturnBOImpl implements ReturnBO {
    @Override
    public boolean addReturn(ReturnDTO returnDTO) {
        return false;
    }

    @Override
    public boolean deleteReturn(String borrowalId) {
        return false;
    }

    @Override
    public boolean updateReturn(ReturnDTO returnDTO) {
        return false;
    }

    @Override
    public ReturnDTO searchReturn(String borrowalId) {
        return null;
    }
}
