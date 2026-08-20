package org.example.spring_mvc.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.spring_mvc.model.entity.Teacher;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    @NotBlank(message= "Name Cannot be empty")
    private String name;
    @NotBlank(message = "email cannot be empty")
    @Email(message="Invalid Email Format")
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
