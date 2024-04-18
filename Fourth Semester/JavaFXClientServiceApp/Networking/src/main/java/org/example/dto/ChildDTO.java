package org.example.dto;

import java.io.Serializable;

public class ChildDTO implements Serializable {
    private Integer childID;
    private String name;
    private Integer age;

    public ChildDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getChildID() {
        return childID;
    }

    public void setChildID(Integer childID) {
        this.childID = childID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ChildDTO{" +
                "childID=" + childID +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
