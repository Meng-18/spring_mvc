package org.example.spring_mvc.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.spring_mvc.model.respone.StudentResponse;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gmail;
    private String address;

    public Student(Long id, String name, String gmail, String address) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.address = address;
    }

    public Student() {
    }

    public Student(String name, String gmail, String address) {
        this.name = name;
        this.gmail = gmail;
        this.address = address;
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void toEntity(Student student) {

    }

    public StudentResponse toResponse() {

        return new StudentResponse(id, name, gmail, address);
    }
}

