package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MemberDTO;

public interface MemberBO extends SuperBO {
    boolean saveMember(MemberDTO memberDTO);

    boolean updateMember(MemberBO memberBO);

    MemberDTO searchMember(String id);

    boolean deleteMember(String id);
}
