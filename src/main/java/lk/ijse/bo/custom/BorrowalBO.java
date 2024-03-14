package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BorrowalDTO;

public interface BorrowalBO extends SuperBO {
    boolean addBorrowal(BorrowalDTO borrowalDTO);

    boolean deleteBorrowal(String id);

    boolean updateBorrowal(BorrowalDTO borrowalDTO);

    BorrowalDTO searchBorrowal(String id);
}
