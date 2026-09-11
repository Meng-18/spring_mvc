package org.example.spring_mvc.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.entity.Card;
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
    private String email;
    private String address;

    @Valid
    @NotNull(message = "Card is required")
    private CardRequest cardRequest;
    public Student toEntity(String code) {
        Card card = new Card(cardRequest.getIssueDate() , cardRequest.getExpiryDate(),code);
        return new Student(name,email,address,card);
    }
}
