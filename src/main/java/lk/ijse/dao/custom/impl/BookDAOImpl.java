package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.save(book);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally{
            session.close();
        }

    }

    @Override
    public boolean update(Book book) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.update(book);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally{
            session.close();
        }

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

        try{
            session.remove(book);
            transaction.commit();
            return true;
        }catch (Exception e){
            return false;
        }finally{
            session.close();
        }

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

    @Override
    public List<Book> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Book");
        nativeQuery.addEntity(Book.class);
        List<Book> books = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return books;
    }

    @Override
    public List<Book> getAvailableBooks() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Book WHERE quantity>0");
        nativeQuery.addEntity(Book.class);
        List<Book> books = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return books;
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
