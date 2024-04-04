package org.example;

public class User implements Entity<Integer> {
    private String username;
    private String password;

    private Integer userID;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public void setEntityID(Integer integer) {
        this.userID = integer;
    }

    @Override
    public Integer getEntityID() {
        return userID;
    }

    @Override
    public String toString() {
        return "User: " + userID + ' ' + username + ' ' + password + ' ';
    }
}
