package lk.ijse.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.tm.OverdueTM;

import java.util.List;

public interface OverdueBO extends SuperBO {
    ObservableList<OverdueTM> getAllOverdues();
}
