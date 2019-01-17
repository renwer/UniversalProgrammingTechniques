package com.company.impl;

import com.company.CrudRepository;
import com.company.model.Group;
import com.company.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements CrudRepository<User, String> {

    Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findById(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE user_login = '" + login + "'");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public List<String> findByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS WHERE user_login LIKE '" + name + "'");
            ResultSet rs = statement.executeQuery();
            List<String> result = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                result.add(rs.getString(i));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void add(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT OR IGNORE INTO USERS (user_login, user_password)" +
                    " VALUES ('" + user.getUser_login() + "', '" + user.getUser_password() + "')");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(User user) {
        try {
            //logic: get the previous user-groups entries in the table, find the difference with the new user groups,
            //insert or delete new rows into the join table accordingly
            PreparedStatement findGroupsInGroupsUsers = connection.prepareStatement("SELECT group_id FROM GROUPS_USERS " +
                    "WHERE user_login = '" + user.getUser_login() + "'");
            ResultSet oldUsersGroupsResultSet = findGroupsInGroupsUsers.executeQuery();
            List<Long> oldUserGroups = new ArrayList<>();
            int i = 1;
            while (oldUsersGroupsResultSet.next()) {
                oldUserGroups.add(oldUsersGroupsResultSet.getLong(1));
                i++;
            }
            //If the number of groups increased before updating, insert the last group
            if (user.getGroups().size() > oldUserGroups.size()) {
                PreparedStatement insertTheNewGroup = connection.prepareStatement("INSERT OR IGNORE INTO GROUPS_USERS(group_id, user_login)" +
                        " VALUES(" + user.getGroups().get(user.getGroups().size()-1).getGroup_id() + ", '" + user.getUser_login() + "')");
                insertTheNewGroup.execute();
            } else if (user.getGroups().size() < oldUserGroups.size()) {
                //in this case, remove the outdated group record
                PreparedStatement deleteOldGroup = connection.prepareStatement("DELETE FROM GROUPS_USERS " +
                        "WHERE group_id = " + oldUserGroups.get(oldUserGroups.size()-1));
                deleteOldGroup.execute();
            }
            //also update the rest of the user's updatable data(the password).
            PreparedStatement updatePassword = connection.prepareStatement("UPDATE USERS SET user_password = '"
                    + user.getUser_password() + "' WHERE user_login = '" + user.getUser_login() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            //remove all group entries where this user was present
            PreparedStatement leaveAllGroups = connection.prepareStatement("DELETE FROM GROUPS_USERS WHERE user_login = '" +user.getUser_login() + "'");
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM USERS WHERE user_login = '" + user.getUser_login() + "'");
            leaveAllGroups.execute();
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
