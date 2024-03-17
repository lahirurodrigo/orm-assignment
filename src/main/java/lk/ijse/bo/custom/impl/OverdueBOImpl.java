package lk.ijse.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.bo.custom.OverdueBO;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.tm.OverdueTM;
import lk.ijse.entity.Borrowals;

import java.util.ArrayList;
import java.util.List;

public class OverdueBOImpl implements OverdueBO {

    QueryDAO queryDAO = new QueryDAOImpl();
    @Override
    public ObservableList<OverdueTM> getAllOverdues() {

        ObservableList<OverdueTM> list = FXCollections.observableArrayList();

        List<Borrowals> borrowals = queryDAO.getAllOverdues();

        if (borrowals.size()==0){
            return list;
        }

        for (Borrowals borrowal : borrowals){
            list.add(new OverdueTM(
                    borrowal.getId(),
                    borrowal.getMember().getId(),
                    borrowal.getMember().getName(),
                    borrowal.getBooks().get(0).getId(),
                    borrowal.getBooks().get(1).getId(),
                    borrowal.getBorrow_date(),
                    borrowal.getDue_date()
            ));
        }

        return list;
    }
}
