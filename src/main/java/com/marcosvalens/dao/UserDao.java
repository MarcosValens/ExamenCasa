package com.marcosvalens.dao;

import com.marcosvalens.model.Rol;
import com.marcosvalens.model.School;
import com.marcosvalens.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DAO<User> {

    private static UserDao userDao;
    private Connection connection = ConnectionMysql.getConnection();
    private String sql;
    private User user = new User();
    List<User> users = new ArrayList<>();

    private UserDao() {
    }

    public synchronized static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private List<User> whileLoop(ResultSet rs) throws SQLException {

        while (rs.next()) {
            try {
                User user = new User();
                user.setUserName(rs.getString("username"));
                School school = SchoolDao.getInstance().getSchool(rs.getInt("escola_idescola"));
                user.setSchool(school);
                user.setPwd(rs.getString("password"));
                user.setName(rs.getString("nom"));
                user.setSecondName(rs.getString("cognoms"));
                user.setPicture(rs.getString("foto"));
                String rol = rs.getString("rol");
                if (rol.equalsIgnoreCase(String.valueOf(Rol.ADMIN))) {
                    user.setRol(Rol.ADMIN);
                } else if (rol.equalsIgnoreCase(String.valueOf(Rol.EDITOR))) {
                    user.setRol(Rol.EDITOR);
                } else user.setRol(null);
                users.add(user);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return users;
    }

    public User authenticated(String userName, String pwd, String schoolId) {
        users.clear();
        sql = "SELECT * FROM user WHERE username=? AND password=? AND escola_idescola=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, pwd);
            preparedStatement.setString(3, schoolId);
            ResultSet rs = preparedStatement.executeQuery();
            users = whileLoop(rs);
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error UserDao.authenticated:");
            ex.printStackTrace();
        }
        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public User getUser(String userName) {
        users.clear();
        sql = "SELECT * FROM user WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            users = whileLoop(rs);
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error UserDao.get:");
            ex.printStackTrace();
        }
        return users.get(0);
    }

    @Override
    public List<User> getAll() {
        users.clear();
        sql = "SELECT * FROM user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            users = whileLoop(rs);
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Error UserDao.get:");
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {
        this.user = user;
        sql = "INSERT INTO user (username, escola_idescola, password, nom, cognoms, rol) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setLong(2, user.getSchool().getId());
            preparedStatement.setString(3, user.getPwd());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setString(6, String.valueOf(user.getRol()).toLowerCase());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void update(User user, User oldUser) {
        this.user = user;
        sql = "UPDATE user SET username=?,escola_idescola=?,password=?,nom=?,cognoms=?,rol=? WHERE username=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setLong(2, user.getSchool().getId());
            preparedStatement.setString(3, user.getPwd());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSecondName());
            preparedStatement.setString(6, String.valueOf(user.getRol()).toLowerCase());
            preparedStatement.setString(7, oldUser.getUserName());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPicture(String path, User user) {
        sql = "UPDATE user SET foto=? WHERE username=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, path);
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        sql = "DELETE FROM user WHERE username=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
