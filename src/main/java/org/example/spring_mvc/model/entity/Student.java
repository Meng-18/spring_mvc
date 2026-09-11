package org.example.spring_mvc.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.spring_mvc.model.response.CardResponse;
import org.example.spring_mvc.model.response.StudentResponse;

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

    public Student(String name, String gmail, String address, Card card) {
        this.name = name;
        this.gmail = gmail;
        this.address = address;
        this.card = card;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    public StudentResponse toResponse() {
        return new StudentResponse(id, name, gmail, address, card.toResponse());
    }
}

