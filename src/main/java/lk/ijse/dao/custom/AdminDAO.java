package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;
import lk.ijse.dto.AdminDTO;

public interface AdminDAO extends SuperDAO {
    boolean save(AdminDTO adminDTO);
}
