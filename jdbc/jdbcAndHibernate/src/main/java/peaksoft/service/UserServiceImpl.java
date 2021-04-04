package peaksoft.service;

import org.hibernate.Session;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;

import java.util.List;

import static peaksoft.util.Util.getSessionFactory;

public class UserServiceImpl implements UserService {
    // UserDao userDao = new UserDaoJdbcImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


    @Override
    public void createUsersTable() {
        //userDao.createUsersTable();
        userDaoHibernate.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        // userDao.dropUsersTable();
        userDaoHibernate.dropUsersTable();
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        // userDao.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        // userDao.removeUserById(id);
        userDaoHibernate.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        // return userDao.getAllUsers();
        List<User> list;
        list = userDaoHibernate.getAllUsers();
        return list;
    }


    @Override
    public void cleanUsersTable() {
        // userDao.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
    }
}