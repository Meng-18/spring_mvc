package org.example.crud_with_db.model.request;

import lombok.Getter;
import lombok.Setter;
import org.example.crud_with_db.Student;

@Setter
@Getter
public class StudentRequest {
    private String name;
    private String gmail;
    private String address;

    public StudentRequest() {
    }

    public StudentRequest(String name, String gmail, String address) {
        this.name = name;
        this.gmail = gmail;
        this.address = address;
    }

    public Student toEntity() {
        return new Student(name,gmail,address);
    }
}
