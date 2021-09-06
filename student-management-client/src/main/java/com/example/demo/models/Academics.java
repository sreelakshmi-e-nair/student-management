package com.example.demo.models;

import java.util.List;

public class Academics {

    private String department;
    private String tutor;
    private List<Subject> subjectList;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "Academics{" +
                "department='" + department + '\'' +
                ", tutor='" + tutor + '\'' +
                ", subjectList=" + subjectList +
                '}';
    }
}
