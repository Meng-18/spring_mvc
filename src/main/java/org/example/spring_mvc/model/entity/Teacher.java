package org.example.spring_mvc.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.response.TeacherResponse;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String subject;


    public TeacherResponse toResponse() {
        return new TeacherResponse(id, name, email,address);
    }

    public Teacher(String name, String email, String address, String subject) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.subject = subject;
    }


}

