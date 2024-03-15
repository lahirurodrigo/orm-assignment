package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.MemberDTO;

public interface SigninBO extends SuperBO {

    boolean saveAdmin(AdminDTO adminDTO);

    boolean saveMember(MemberDTO memberDTO);

    String genarateNextAdminId() throws Exception;
}
