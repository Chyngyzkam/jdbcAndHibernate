package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    //Util connection = new Util();

    public UserDaoHibernateImpl() {


    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = null;
        try {

             session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.createSQLQuery("create table if not exists users("+
                    "id serial primary key,name varchar (40) not null,"+
                    "lastName varchar (40) not null ,"+
                    "age integer not null)").executeUpdate();
            System.out.println("Table successfull created");
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if(session == null){
                session.close();

            }else if (transaction==null){
                transaction.rollback();
            }

        }
    }


    @Override
    public void dropUsersTable(Session getSessionFactory) {
        Transaction transaction = null;

        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table if exists users").executeUpdate();
            System.out.println(" table successfull deleted");

            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);
            System.out.println(user + "Successfullu saved ");
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = null;
        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            users = session.createQuery("from User").list();

            for (User user : users) {
                System.out.println("found " + user);
                transaction.commit();
                session.close();
            }
        } catch (HibernateException e) {
            if (transaction == null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            System.out.println("Users table seccessfully cleaned ");
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }

    }
}
