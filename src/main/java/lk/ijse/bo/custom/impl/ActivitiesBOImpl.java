package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ActivitiesBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.tm.ActivitiesTM;
import lk.ijse.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesBOImpl implements ActivitiesBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.QUERY);
    @Override
    public List<ActivitiesTM> getOverdueMembers() {

        List<ActivitiesTM> list = new ArrayList<>();

        List<Member> overdueMembers = queryDAO.getOverdueMembers();
        for (Member member : overdueMembers){
            list.add(new ActivitiesTM(member.getId(),member.getName()));
        }

        return list;
    }
}
