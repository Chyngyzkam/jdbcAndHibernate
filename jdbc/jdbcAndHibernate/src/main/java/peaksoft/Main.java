package peaksoft;

import org.hibernate.Session;
import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        //UserService userService= new UserServiceImpl();

      //  userService.createUsersTable();


//        userService.saveUser("Chyngyz","Kamarov", (byte) 30);
//        userService.saveUser("Soli", "Saddilaev", (byte) 28);
//        userService.saveUser("Aman", "Shanykulov", (byte) 23);
//       //userService.removeUserById(1);


//        for (User user:userService.getAllUsers()) {
//            System.out.println(user);
//        }
//
//
//        userService.cleanUsersTable();
//
//       userService.dropUsersTable();

        UserDao userDao = new UserDaoHibernateImpl();

       // userDao.createUsersTable();
        userDao.saveUser("Chyngyz", "Kamarov", (byte) 32);
        userDao.saveUser("Aman", "Janykulov", (byte) 23);
       userDao.getAllUsers();
//        userDao.removeUserById(1);
//         userDao.dropUsersTable();
//        userDao.cleanUsersTable();






    }
}
