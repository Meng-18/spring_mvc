package org.example.spring_mvc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.entity.Student;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private String gmail;
    private String address;

    public Student toEntity() {
        return new Student(name,gmail,address);
    }
}
