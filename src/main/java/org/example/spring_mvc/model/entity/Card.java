package org.example.spring_mvc.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.response.CardResponse;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate issueDate;
    private LocalDate expiryDate;
    @Column(unique = true)
    private String code;

    @OneToOne(mappedBy = "card")
    private Student student;

    public Card( LocalDate issueDate, LocalDate expiryDate, String code) {
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.code = code;
    }

    public CardResponse toResponse() {
        return new CardResponse(id,issueDate,expiryDate,code);
    }
}

