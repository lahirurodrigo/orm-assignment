package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BorrowalDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowals;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class BorrowalDAOImpl implements BorrowalDAO {
    @Override
    public boolean save(Borrowals borrowal) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(borrowal);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Borrowals borrowals) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(borrowals);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Borrowals search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Borrowals borrowal = session.get(Borrowals.class,id);

        transaction.commit();
        session.close();

        return borrowal;
    }

    @Override
    public boolean delete(Borrowals borrowals) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(borrowals);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM Borrowals ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    private String splitId(String currentId)throws Exception {
        if(currentId != null) {
            String[] strings = currentId.split("BR0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "BR00"+id;
            }else {
                if (length < 3){
                    return "BR0"+id;
                }else {
                    return "BR"+id;
                }
            }
        }
        return "BR001";
    }
}
