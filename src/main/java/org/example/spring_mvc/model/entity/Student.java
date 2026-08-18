package org.example.spring_mvc.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.respone.StudentResponse;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gmail;
    private String address;

    public Student(String name, String gmail, String address) {
        this.name = name;
        this.gmail = gmail;
        this.address = address;
    }


    public void toEntity(Student student) {

    }

    public StudentResponse toResponse() {

        return new StudentResponse(id, name, gmail, address);
    }
}

