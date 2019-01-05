package com.company.generators;

import com.company.extents.StudentGroups;
import com.company.extents.Students;
import com.company.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class CommonGenerator {

    static String[] names = {"John", "Damien", "Frida", "Jack", "Ross", "Finch", "Jacek", "Nina", "Asia", "Agnieszka"};

    public Set<StudentGroup> groups;
    public Set<Student> students;
    public Set<Teacher> teachers;
    public Set<Department> departments;


    public CommonGenerator() {
        this.groups = get12Groups();
        this.students = getAllStudents(this.groups);
        this.teachers = getAllTeachers();
        this.departments = get3departments();
    }

    private static Set<StudentGroup> get12Groups() {

        Set<StudentGroup> result = new HashSet<>();

        for (int i = 0; i < 12; i++) {
            StudentGroup temp = new StudentGroup("Group " + i);
            for (int k = 0; k < 10; k++) {
                String tempName = names[(int)(Math.random() * names.length)];
                String tempLastName = names[(int)(Math.random() * names.length)];

                LocalDate tempDate = getRandomDate();

                Nationality tempNationality = Nationality.values()[(int)(Math.random() * Nationality.values().length)];
                Student tempStudent = new Student(tempName, tempLastName, tempDate, tempNationality);
                temp.addStudent(tempStudent);
                Students.students.add(tempStudent);
            }
            result.add(temp);
        }
        StudentGroups.studentGroups.addAll(result);
        return result;
    }

    private static Set<Student> getAllStudents(Set<StudentGroup> groups) {

        Set<Student> result = new HashSet<>();
        for (StudentGroup group: groups) {
            result.addAll(group.get_students());
        }
        return result;
    }

    private static Set<Teacher> getAllTeachers() {

        Set<Teacher> result = new HashSet<>();

            for (int k = 0; k < 10; k++) {
                String tempName = names[(int)(Math.random() * names.length)];
                String tempLastName = names[(int)(Math.random() * names.length)];

                LocalDate tempHireDate = getRandomDate();
                LocalDate tempBirthDate = getRandomDate();
                Nationality tempNationality = Nationality.values()[(int)(Math.random() * Nationality.values().length)];
                AcademicDegree tempAcademicDegree = AcademicDegree.values()[(int)(Math.random() * AcademicDegree.values().length)];

                Teacher tempTeacher = new Teacher(tempName, tempLastName, tempBirthDate, tempNationality, tempAcademicDegree, tempHireDate);
                result.add(tempTeacher);
            }
        return result;
    }

    private static Set<Department> get3departments() {

        Set<Department> result = new HashSet<>();
        List<Teacher> teachers = new ArrayList<>(getAllTeachers());
        int third = teachers.size() / 3;
        List<Teacher> firstDepartamentTeachers = teachers.subList(0, third);
        List<Teacher> secondDepartamentTeachers = teachers.subList(third, third*2);
        List<Teacher> thirdDepartamentTeachers = teachers.subList(third*2, teachers.size());
        Department firstDepartament = new Department("Maths");
        for (Teacher t: firstDepartamentTeachers) {
            firstDepartament.addTeacher(t);
        }
        Department secondDepartament = new Department("Physics");
        for (Teacher t: secondDepartamentTeachers) {
            secondDepartament.addTeacher(t);
        }
        Department thirdDepartament = new Department("Informatics");
        for (Teacher t: thirdDepartamentTeachers) {
            thirdDepartament.addTeacher(t);
        }
        result.add(firstDepartament);
        result.add(secondDepartament);
        result.add(thirdDepartament);
        return result;
    }


    private static LocalDate getRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
