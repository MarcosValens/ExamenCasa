package com.marcosvalens.model;

import java.io.Serializable;
import java.util.Objects;

public class School implements Serializable,Cloneable {
    long id;
    String name;

    public School() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id &&
                Objects.equals(name, school.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public School clone() throws CloneNotSupportedException {
        return (School) super.clone();
    }
}
