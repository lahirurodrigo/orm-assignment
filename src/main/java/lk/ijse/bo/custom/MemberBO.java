package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.MemberDTO;

import java.util.List;

public interface MemberBO extends SuperBO {
    boolean saveMember(MemberDTO memberDTO);

    boolean updateMember(MemberDTO memberDTO);

    MemberDTO searchMember(String id);

    boolean deleteMember(MemberDTO memberDTO);

    String generateNextMemberID() throws Exception;

}
