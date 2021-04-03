package peaksoft.dao;

import org.hibernate.Session;
import peaksoft.model.User;

import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable(Session getSessionFactory);

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
