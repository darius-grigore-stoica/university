package org.example;

public class Child implements Entity<Integer> {
    private String name;
    private Integer age;
    private Integer childID;

    public Child(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void setEntityID(Integer integer) {
        this.childID = integer;
    }

    @Override
    public Integer getEntityID() {
        return childID;
    }

    @Override
    public String toString() {
        return "Child = (" +
                "name = " + name +
                ", age =" + age +
                ", childID =" + childID +
                ')';
    }
}
