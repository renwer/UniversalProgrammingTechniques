package com.company;

import java.util.Objects;

public class Student implements IAggregable<Student, Integer>, IDeeplyCloneable<Student> {

    private String name;
    private int totalGrade = 0;

    //empty constructor to allow deep cloning, only available privately to deepClone()
    private Student() {
    }

    public Student(String name) {
        this.name = name;
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Invalid input in Student constructor!");
        }
    }

    //aggregate for students should produce the sum of their total grades
    @Override
    public Integer aggregate(Integer intermediateResult) {
        if (intermediateResult == null) {
            return totalGrade;
        }
        return totalGrade + intermediateResult;
    }

    @Override
    public Student deepClone() {
        Student copy = new Student();
        copy.name = this.name;
        return copy;
    }

    //overridden "equals" and "hashCode" for reliably comparing equal students
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return totalGrade == student.totalGrade &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, totalGrade);
    }

    //not quite a clean function due to side effects (?)
    public void addGrade(int newGrade) {
        //input validation and exception throwing for negative input
        if (newGrade >= 0) {
            this.totalGrade += newGrade;

        } else throw new RuntimeException("Negative grades are not allowed!");
    }
}
