package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReturnBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReturnsDAO;
import lk.ijse.dao.custom.impl.ReturnDAOImpl;
import lk.ijse.dto.ReturnDTO;

public class ReturnBOImpl implements ReturnBO {

    ReturnsDAO returnsDAO = (ReturnsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.RETURNS);
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

    @Override
    public String generateNextReturnID() throws Exception {
        return returnsDAO.generateNext();
    }
}
