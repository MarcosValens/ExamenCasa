package com.marcosvalens.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable,Cloneable {
    String userName;
    School school;
    String pwd;
    String name;
    String secondName;
    String picture;
    Rol rol;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(school, user.school) &&
                Objects.equals(pwd, user.pwd) &&
                Objects.equals(name, user.name) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(rol, user.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, school, pwd, name, secondName, rol);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", school=" + school +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
