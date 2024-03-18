package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.entity.Borrowals;
import lk.ijse.entity.Member;
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
            transaction.commit();

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
        transaction.commit();
        session.close();
        return borrowals;
    }

    @Override
    public List<Member> getValidMembers() {

        List<Member> members = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT DISTINCT m\n" +
                "FROM Member m\n" +
                "LEFT JOIN Borrowals b ON m = b.member\n" +
                "LEFT JOIN Returns r ON b = r.borrowals\n" +
                "GROUP BY m.id\n" +
                "HAVING COUNT(b) = 0 OR COUNT(r) = COUNT(b)");

        List<Member> list = query.getResultList();
        for (Member member : list ){
            System.out.println(member.getId()+"\n");
        }
        transaction.commit();
        session.close();


        return list;

    }

    @Override
    public List<Member> getOverdueMembers(){
        List<Member> members = new ArrayList<>();

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT DISTINCT m\n " +
                "FROM Member m \n" +
                "JOIN Borrowals b ON m = b.member \n" +
                "LEFT JOIN Returns r ON b = r.borrowals \n" +
                "WHERE (r IS NULL AND b.due_date < :date) OR r.return_date > b.due_date");
        query.setParameter("date",LocalDate.now());
        members = query.getResultList();

        transaction.commit();
        session.close();

        for (Member m : members){
            System.out.println(m.getId()+"\n");
        }

        return members;
    }
}
