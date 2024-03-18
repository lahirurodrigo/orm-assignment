package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

public interface ReturnsDAO extends SuperDAO {
    String generateNext() throws Exception;
}
