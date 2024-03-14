package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MemberBO;
import lk.ijse.dto.MemberDTO;

public class MemberBOImpl implements MemberBO {
    @Override
    public boolean saveMember(MemberDTO memberDTO) {
        return false;
    }

    @Override
    public boolean updateMember(MemberBO memberBO) {
        return false;
    }

    @Override
    public MemberDTO searchMember(String id) {
        return null;
    }

    @Override
    public boolean deleteMember(String id) {
        return false;
    }
}
