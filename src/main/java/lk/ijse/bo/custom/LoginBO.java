package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDTO;

public interface LoginBO extends SuperBO {
    AdminDTO checkCredentials(String username);
}
