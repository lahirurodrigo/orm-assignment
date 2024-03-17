package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.Borrowals;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

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

    @Override
    public List<Borrowals> getAllOverdues() {

        LocalDate date = LocalDate.now();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT b " +
                "FROM Borrowals b " +
                "LEFT JOIN Returns r ON b = r.borrowals " +
                "WHERE r IS NULL AND b.due_date < :currentDate");

        query.setParameter("currentDate", date);

        List<Borrowals> borrowals = query.getResultList();

        return borrowals;
    }
}
