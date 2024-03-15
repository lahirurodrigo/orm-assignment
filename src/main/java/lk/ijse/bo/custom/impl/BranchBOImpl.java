package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.impl.AdminDAOImpl;
import lk.ijse.dao.custom.impl.BranchDAOImpl;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = new BranchDAOImpl();

    AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public boolean saveBranch(BranchDTO branchDTO) {

        Admin admin = adminDAO.search(branchDTO.getAdmin_id());

        return branchDAO.save(new Branch(
           branchDTO.getId(),
           branchDTO.getName(),
           admin
        ));
    }

    @Override
    public boolean updateBranch(BranchDTO branchDTO) {

        Admin admin = adminDAO.search(branchDTO.getAdmin_id());

        return branchDAO.update(new Branch(
                branchDTO.getId(),
                branchDTO.getName(),
                admin
        ));
    }

    @Override
    public BranchDTO searchBranch(String id) {

        Branch branch = branchDAO.search(id);

        return new BranchDTO(
                branch.getId(),
                branch.getName(),
                branch.getAdmin().getId()
        );
    }

    @Override
    public boolean deleteBranch(BranchDTO branchDTO) {

        Admin admin = adminDAO.search(branchDTO.getAdmin_id());

        return branchDAO.delete(new Branch(
                branchDTO.getId(),
                branchDTO.getName(),
                admin
        ));
    }

    @Override
    public String generateNextBranchID() throws Exception {
        return branchDAO.genatareNextId();
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchDAO.getAll();
        List<BranchDTO> branchDto = new ArrayList<>();

        for(Branch branch : branches){
            branchDto.add(new BranchDTO(
                    branch.getId(),
                    branch.getName(),
                    branch.getAdmin().getId()
            ));
        }
        return branchDto;
    }

    @Override
    public List<String> getAllAdmins() {
        List<Admin> admins = adminDAO.getAll();

        List<String> ids = new ArrayList<>();

        for (Admin admin : admins){
            ids.add(admin.getId());
        }

        return ids;
    }
}
