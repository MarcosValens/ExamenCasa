package com.marcosvalens.dao;

import com.marcosvalens.model.School;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchoolDao implements DAO<School> {
    private static SchoolDao SchoolDao;
    private Connection connection = ConnectionMysql.getConnection();
    private String sql;

    private SchoolDao(){}

    public synchronized static SchoolDao getInstance(){
        if (SchoolDao == null){
            SchoolDao = new SchoolDao();
        }
        return SchoolDao;
    }

    public School getByName(String name){
        sql = "SELECT * FROM escola WHERE nom = ?";
        School school = new School();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                school.setId(rs.getLong("idescola"));
                school.setName(rs.getString("nom"));
            }
        } catch (SQLException e){
            System.out.println("Error SchoolDao.getByName: " + e.getMessage());
        }
        return school;
    }
    @Override
    public School getSchool(long id) {
        sql = "SELECT * FROM escola WHERE idescola = ?";
        School school = new School();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                school.setId(rs.getLong("idescola"));
                school.setName(rs.getString("nom"));
            }
        } catch (SQLException e){
            System.out.println("Error UserDao.get: " + e.getMessage());
        }
        return school;
    }

    @Override
    public List<School> getAll() {
        sql = "SELECT * FROM escola";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            List<School> schools = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                try {
                    School school = new School();
                    school.setName(rs.getString("nom"));
                    school.setId(Long.parseLong(rs.getString("idescola")));
                    schools.add(school);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            preparedStatement.close();
            return schools;
        } catch (SQLException ex) {
            System.out.println("Error EscolaDao.getAll:");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(School school) {

    }

    @Override
    public void update(School school, School u) {

    }

    @Override
    public void delete(School school) {

    }
}
