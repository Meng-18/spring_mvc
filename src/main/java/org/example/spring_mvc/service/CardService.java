package org.example.spring_mvc.service;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.spring_mvc.model.response.CardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface CardService {
    Page<CardResponse> list(@Min(1) int page, @Min(1) @Max(100) int size, Sort.Direction direction);
}
