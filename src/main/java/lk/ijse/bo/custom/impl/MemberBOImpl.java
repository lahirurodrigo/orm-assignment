package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MemberBO;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.dao.custom.impl.MemberDAOImpl;
import lk.ijse.dto.MemberDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    public boolean saveMember(MemberDTO memberDTO) {

        List<Borrowals> borrowals = new ArrayList<>();

        return memberDAO.save(new Member(
                memberDTO.getId(),
                memberDTO.getUsername(),
                memberDTO.getEmail(),
                borrowals
        ));
    }

    @Override
    public boolean updateMember(MemberDTO memberDTO) {

        Member member = memberDAO.search(memberDTO.getId());

        return memberDAO.update(new Member(
                memberDTO.getId(),
                memberDTO.getUsername(),
                memberDTO.getEmail(),
                member.getBorrowals()
        ));
    }

    @Override
    public MemberDTO searchMember(String id) {
        Member member = memberDAO.search(id);
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }

    @Override
    public boolean deleteMember(MemberDTO memberDTO) {

        Member member = memberDAO.search(memberDTO.getId());

        return memberDAO.delete(new Member(
                memberDTO.getId(),
                memberDTO.getUsername(),
                memberDTO.getEmail(),
                member.getBorrowals()
        ));

    }

    @Override
    public String generateNextMemberID() throws Exception {
        return memberDAO.generateNextId();
    }
}
