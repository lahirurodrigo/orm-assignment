package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    public static  BOFactory boFactory;

    public BOFactory() {

    }

    public static BOFactory getBoFactory(){
        return (boFactory ==null) ? boFactory=new BOFactory() : boFactory;
    }
    public enum BOTypes{
        ACTIVITY,BOOK,BORROW,BRANCH,LOGIN,MEMBER,OVERDUE,RETURN,SIGNIN
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case ACTIVITY:
                return new ActivitiesBOImpl();
            case BOOK:
                return new BookBOImpl();
            case BORROW:
                return new BorrowalBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case OVERDUE:
                return new OverdueBOImpl();
            case RETURN:
                return new ReturnBOImpl();
            case SIGNIN:
                return new SigninBOImpl();
            default:
                return null;
        }

    }
}
