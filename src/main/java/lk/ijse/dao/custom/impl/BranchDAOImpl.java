package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class BranchDAOImpl implements BranchDAO {

    @Override
    public String genatareNextId()throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM Branch ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id != null) {
            return splitId(id);
        } else {
            return splitId(null);
        }
    }

    private String splitId(String currentId)throws Exception{
        if (currentId != null) {
            String[] strings = currentId.split("BR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "BR00" + id;
            } else {
                if (length < 3) {
                    return "BR0" + id;
                } else {
                    return "BR" + id;
                }
            }
        }
        return "BR001";
    }

    @Override
    public List<Branch> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Branch");
        nativeQuery.addEntity(Branch.class);
        List<Branch> branches = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return branches;
    }

    @Override
    public boolean save(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(branch);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(branch);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Branch search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Branch branch = session.get(Branch.class,id);

        transaction.commit();
        session.close();

        return branch;
    }

    @Override
    public boolean delete(Branch branch) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(branch);

        transaction.commit();
        session.close();

        return true;
    }
}
