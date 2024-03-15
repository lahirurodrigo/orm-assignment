package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ReturnDTO;

public interface ReturnBO extends SuperBO {
    boolean addReturn(ReturnDTO returnDTO);

    boolean deleteReturn(String borrowalId);

    boolean updateReturn(ReturnDTO returnDTO);

    ReturnDTO searchReturn(String borrowalId);

    String generateNextReturnID() throws Exception;
}
