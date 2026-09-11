package org.example.spring_mvc.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;
    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;
}
