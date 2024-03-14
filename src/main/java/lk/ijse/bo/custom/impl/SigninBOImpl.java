package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SigninBO;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.MemberDTO;

public class SigninBOImpl implements SigninBO {

    AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean saveAdmin(AdminDTO adminDTO) {
        boolean isSaved = adminDAO.save(adminDTO);

        return isSaved;
    }

    @Override
    public boolean saveMember(MemberDTO memberDTO) {
        return false;
    }
}
