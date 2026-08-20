package org.example.spring_mvc.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message= "Name Cannot be empty")
    private String name;
    @NotBlank(message = "email cannot be empty")
    @Email(message="Invalid Email Format")
    private String gmail;
    private String address;

    public Student toEntity() {
        return new Student(name,gmail,address);
    }
}
