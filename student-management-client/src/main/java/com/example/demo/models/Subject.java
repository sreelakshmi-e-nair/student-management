package com.example.demo.models;

public class Subject {

    private String name;
    private String mentor;
    private int attendance;
    private int internals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getInternals() {
        return internals;
    }

    public void setInternals(int internals) {
        this.internals = internals;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", mentor='" + mentor + '\'' +
                ", attendance=" + attendance +
                ", internals=" + internals +
                '}';
    }
}
