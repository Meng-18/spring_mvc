package org.example.spring_mvc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.entity.Teacher;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private String name;
    private String email;
    private String address;
    private String subject;

    public Teacher toEntity() {
        return new Teacher(name, email, address, subject);
    }

    public Teacher toUpdate(TeacherRequest request) {
        return new Teacher(name, email, address, subject);
    }
}
