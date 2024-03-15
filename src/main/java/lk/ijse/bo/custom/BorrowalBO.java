package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BorrowalDTO;

import java.util.List;

public interface BorrowalBO extends SuperBO {
    boolean addBorrowal(BorrowalDTO borrowalDTO);

    boolean deleteBorrowal(BorrowalDTO borrowalDTO );

    boolean updateBorrowal(BorrowalDTO borrowalDTO);

    BorrowalDTO searchBorrowal(String id);

    String generateNextBorrowalId() throws Exception;

    List<String> loadAllMembers();

    List<String> loadAllBooks();

    List<String> loadAvailableBooks();
}
