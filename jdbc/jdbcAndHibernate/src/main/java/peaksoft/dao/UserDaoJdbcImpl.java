package peaksoft.dao;


import peaksoft.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static peaksoft.util.Util.connect;

public class UserDaoJdbcImpl implements UserDao {


    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String createTable = "create table if not exists users("+
                "id serial primary key,name varchar (40) not null,"+
                "lastName varchar (40) not null ,"+
                "age integer not null)";
        try (Connection conn =connect();
             Statement stmt = conn.createStatement()){
            stmt.executeUpdate(createTable);
            System.out.println("added Table");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void dropUsersTable() {
        String dropTable = "drop table if  exists users";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(dropTable)) {
            stmt.executeUpdate();
            System.out.println("deleted Table");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String addUser = "insert into users(name, lastName,age )" +
                "values (?,?,?)";
        try (Connection conn =connect();
             PreparedStatement stmt = conn.prepareStatement(addUser)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
            System.out.println(name + " " + lastName + "added to users");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String delete = "delete from users where id= ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(delete)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("removed users by id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String getAll = "select * from users";
        try (Connection conn =connect();
             Statement stmt = conn.createStatement();
             ResultSet resSet = stmt.executeQuery(getAll)){
            while (resSet.next()) {
                User user = new User();
                user.setId((long) resSet.getInt("id"));
                user.setName(resSet.getString("name"));
                user.setLastName(resSet.getString("lastName"));
                user.setAge(resSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }


    public void cleanUsersTable() {
        String clean ="delete from users";
        try(Connection conn =connect();
            PreparedStatement stmt = conn.prepareStatement(clean)){
            stmt.executeUpdate();
            System.out.println("Deleted all users");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}