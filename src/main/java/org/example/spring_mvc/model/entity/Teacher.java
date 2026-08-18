package org.example.spring_mvc.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.respone.TeacherResponse;

@Entity
@Getter
@Setter
@NoArgsConstructor
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


    public Teacher toUpdate(TeacherRequest request) {
        this.setName(request.getName());
        this.setEmail(request.getEmail());
        this.setAddress(request.getAddress());
        this.setSubject(request.getSubject());
        return this;
    }

    public TeacherResponse toResponse() {
        return new TeacherResponse(id, name, email,address, subject);
    }
}

