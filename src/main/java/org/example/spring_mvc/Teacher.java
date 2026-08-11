package org.example.spring_mvc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.spring_mvc.model.request.TeacherRequest;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String subject;


    public Teacher(Long id, String name, String email, String address, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.subject = subject;
    }

    public Teacher(String name, String email, String address, String subject) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.subject = subject;
    }

    public Teacher() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Teacher toUpdate(TeacherRequest request) {
        this.setName(request.getName());
        this.setEmail(request.getEmail());
        this.setAddress(request.getAddress());
        this.setSubject(request.getSubject());
        return this;
    }
}

