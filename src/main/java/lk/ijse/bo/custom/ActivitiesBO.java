package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.tm.ActivitiesTM;

import java.util.List;

public interface ActivitiesBO extends SuperBO {
    List<ActivitiesTM> getOverdueMembers();
}
