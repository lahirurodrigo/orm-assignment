package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(book);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(book);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Book search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class,id);

        transaction.commit();
        session.close();

        return book;
    }

    @Override
    public boolean delete(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(book);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM Book ORDER BY id DESC LIMIT 1");
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
            String[] strings = currentId.split("B0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "B00"+id;
            }else {
                if (length < 3){
                    return "B0"+id;
                }else {
                    return "B"+id;
                }
            }
        }
        return "B001";
    }
}
