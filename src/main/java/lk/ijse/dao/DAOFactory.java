package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    public static DAOFactory daoFactory;

    public DAOFactory() {

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DTOTypes{
        ADMIN,BOOK,BORROW,BRANCH,QUERY,USER,RETURNS
    }

    public SuperDAO getDAO(DTOTypes dtoTypes){
        switch (dtoTypes){
            case ADMIN:
                return new AdminDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BORROW:
                return new BorrowalDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case USER:
                return new MemberDAOImpl();
            case RETURNS:
                return new ReturnDAOImpl();
            default:
                return null;

        }

    }
}
