package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.MemberDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public boolean save(Member member) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(member);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public Member search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Member member = session.get(Member.class,id);

        transaction.commit();
        session.close();

        return member;
    }

    @Override
    public boolean update(Member member) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(member);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(Member member) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(member);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNextId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<String> nativeQuery = session.createNativeQuery("SELECT id FROM Member ORDER BY id DESC LIMIT 1");
        String id = nativeQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null) {
            return splitId(id);
        }else{
            return splitId(null);
        }
    }

    private String splitId(String currentId) throws Exception{
        if(currentId != null) {
            String[] strings = currentId.split("M0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "M00"+id;
            }else {
                if (length < 3){
                    return "M0"+id;
                }else {
                    return "M"+id;
                }
            }
        }
        return "M001";
    }

}
