package com.company.impl;

import com.company.CrudRepository;
import com.company.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryImpl implements CrudRepository<Group, Long> {

    Connection connection;

    public GroupRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findById(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GROUPS WHERE group_id = " + id);
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM GROUPS WHERE group_name LIKE '%" + name + "%'");
            ResultSet rs = statement.executeQuery();
            List<String> result = new ArrayList<>();
            int i = 1;
            while (rs.next()) {
                String s = rs.getString(i);
                //why can't I cast this?
                result.add(s);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT OR IGNORE INTO GROUPS (group_id, group_name, group_description)" +
                    " VALUES (" + group.getGroup_id() + ", '" + group.getGroup_name() + "', '" + group.getGroup_description() + "')");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //groups should not have their user arrays updated without the user's intention, so the handling of the joint
    //table is done in the user repository update method
    @Override
    public void update(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE GROUPS SET " +
                    "group_name = '" + group.getGroup_name() + "', group_description = '" + group.getGroup_description() +
                    "' WHERE group_id = " + group.getGroup_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Group group) {
        try {
            PreparedStatement deleteAllUsersFromTheGroup = connection.prepareStatement("DELETE FROM GROUPS_USERS WHERE group_id = " + group.getGroup_id());
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM GROUPS WHERE group_id = " + group.getGroup_id());
            deleteAllUsersFromTheGroup.execute();
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
