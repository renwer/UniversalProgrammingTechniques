package com.company.model;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final String user_login;
    private String user_password;
    private List<Group> groups;

    public User(String user_login, String user_password) {
        this.user_login = user_login;
        this.user_password = user_password;
        this.groups = new ArrayList<>();
    }

    public void joinAGroup(Group group) {
        this.groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getUser_login() {
        return user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
}
