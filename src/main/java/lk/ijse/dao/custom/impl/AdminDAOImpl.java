package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean save(Admin admin) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(admin);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM Admin ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    @Override
    public boolean check(Admin admin) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            NativeQuery<String> query = session.createNativeQuery("select password from Admin where name = :name");
            query.setParameter("name", admin.getName());
            String password = query.uniqueResult();

            transaction.commit();

            if (password.equals(admin.getPassword())) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public List<Admin> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Admin");
        nativeQuery.addEntity(Admin.class);
        List<Admin> admins  = nativeQuery.getResultList();

        transaction.commit();
        session.close();

        return admins;
    }

    @Override
    public Admin search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Admin admin = session.get(Admin.class,id);

        transaction.commit();
        session.close();

        return admin;
    }

    private String splitId(String currentId)throws Exception{
        if(currentId != null) {
            String[] strings = currentId.split("A0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "A00"+id;
            }else {
                if (length < 3){
                    return "A0"+id;
                }else {
                    return "A"+id;
                }
            }
        }
        return "A001";
    }
}
