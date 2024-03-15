package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.Borrowals;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    public List<Object[]> getTransaction(String user)throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("" +
                "SELECT u.name,bd.book_id,bk.title,bd.borrowDate,bd.returnDate,bd.status,b.user_id\n" +
                "FROM book_detils bd\n" +
                "JOIN borrow b on bd.borrow_borrowId = b.borrowId\n" +
                "join book bk on bd.book_id = bk.id\n" +
                "join user u on b.user_id = u.id\n" +
                "WHERE u.id = :user");

        nativeQuery.setParameter("user",user);

        List<Object[]> tran = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return tran;
    }

    public List<Object[]> getAllTimeOut() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT u.name,bd.book_id,bk.title,bd.borrowDate,bd.returnDate\n" +
                "FROM book_detils bd\n" +
                "         JOIN borrow b on bd.borrow_borrowId = b.borrowId\n" +
                "         JOIN user u on b.user_id = u.id\n" +
                "         JOIN book bk on bd.book_id = bk.id\n" +
                "WHERE bd.returnDate < CURRENT_DATE() and bd.status= \"Borrow\";");


        List<Object[]> tran = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return tran;

    }

    @Override
    public List<Object[]> loadDueBorrowals() {

        Session session = null;
        Transaction transaction = null;

        List<Borrowals> borrowals = new ArrayList<>();

        List<Object[]> objects = null;

        try{
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT b.*\n" +
                    "FROM Borrowals b\n" +
                    "LEFT JOIN Returns r ON b.id = r.borrowal_id\n" +
                    "WHERE r.borrowal_id IS NULL;");

            objects = nativeQuery.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return objects;
    }
}
