package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;

import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ADMIN);
    @Override
    public boolean checkCredentials(String username, String password) throws Exception {
        return adminDAO.check(new Admin("",username,password,new ArrayList<Branch>()));
    }

}
