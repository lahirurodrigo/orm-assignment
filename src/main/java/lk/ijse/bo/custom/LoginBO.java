package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(String username, String password) throws Exception;
}
