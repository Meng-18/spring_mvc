package org.example.spring_mvc.model.request;

import org.example.spring_mvc.model.entity.Teacher;

public class TeacherRequest {
    private String name;
    private String email;
    private String address;
    private String subject;

    public TeacherRequest() {
    }

    public TeacherRequest(String name, String email, String address, String subject) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Teacher toEntity() {
        return new Teacher(name, email, address, subject);
    }

    public Teacher toUpdate(TeacherRequest request) {
        return new Teacher(name, email, address, subject);
    }
}
