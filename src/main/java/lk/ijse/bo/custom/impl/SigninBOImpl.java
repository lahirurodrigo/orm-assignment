package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SigninBO;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.MemberDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;

import java.util.ArrayList;

public class SigninBOImpl implements SigninBO {

    AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean saveAdmin(AdminDTO adminDTO) {
        boolean isSaved = adminDAO.save(new Admin(adminDTO.getId(),adminDTO.getName(),adminDTO.getPassword(),new ArrayList<Branch>()));

        return isSaved;
    }

    @Override
    public boolean saveMember(MemberDTO memberDTO) {
        return false;
    }

    @Override
    public String genarateNextAdminId() throws Exception {
        return adminDAO.generateNextId();
    }
}
