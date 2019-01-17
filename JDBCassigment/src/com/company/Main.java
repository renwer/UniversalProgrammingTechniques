package com.company;

import com.company.impl.GroupRepositoryImpl;
import com.company.impl.UserRepositoryImpl;
import com.company.model.Group;
import com.company.model.User;

import java.sql.*;

public class Main {

    private static Connection connection = null;
    //initialize the database within the static block
    static {
        connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement createUserTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS USERS(user_login TEXT PRIMARY KEY, user_password TEXT)");
            PreparedStatement createGroupTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS GROUPS(group_id NUMBER PRIMARY KEY, group_name TEXT, group_description TEXT)");
            PreparedStatement createGroupsUsersTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS GROUPS_USERS(group_id NUMBER, user_login TEXT," +
                    "CONSTRAINT FK_group_id FOREIGN KEY (group_id) REFERENCES GROUPS (group_id), " +
                    "CONSTRAINT FK_user_login FOREIGN KEY (user_login) REFERENCES USERS (user_login))");
            createUserTable.execute();
            createGroupTable.execute();
            createGroupsUsersTable.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        UserRepositoryImpl userRepository = new UserRepositoryImpl(connection);
        GroupRepositoryImpl groupRepository = new GroupRepositoryImpl(connection);

        User adam = new User("Adam", "password");
        User ewa = new User("Ewa", "unbreakablePassword");
        userRepository.add(adam);
        userRepository.add(ewa);
        userRepository.add(new User("Lady", "Lovelace"));
        userRepository.add(new User("Charles", "Babbage"));
        userRepository.add(new User("Charles", "Boole"));
        userRepository.add(new User("Alan", "Turing"));

        Group mathematicians = new Group(1L, "Mathematicians", "Well-known contributors " +
                "to science and technology.");
        groupRepository.add(mathematicians);
        Group biblicalCharacters = new Group(2L, "Biblical characters", "Characters from the Bible");
        groupRepository.add(biblicalCharacters);

        adam.joinAGroup(biblicalCharacters);
        userRepository.update(adam);

        ewa.joinAGroup(biblicalCharacters);
        userRepository.update(ewa);
        ewa.joinAGroup(mathematicians);
        userRepository.update(ewa);

        ewa.joinAGroup(biblicalCharacters);
        userRepository.update(ewa);

        System.out.println(groupRepository.findByName("math"));
        System.out.println(userRepository.findByName("Adam"));
        System.out.println(userRepository.findById("Charles"));

        PreparedStatement clearupUsers = connection.prepareStatement("DROP TABLE IF EXISTS USERS");
        PreparedStatement clearupGroups = connection.prepareStatement("DROP TABLE IF EXISTS GROUPS");
        PreparedStatement clearupGroupsUsers = connection.prepareStatement("DROP TABLE IF EXISTS GROUPS_USERS");

        //clearupUsers.execute();
        //clearupGroups.execute();
        //clearupGroupsUsers.execute();
        connection.close();
    }
}
