package org.example.spring_mvc.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.spring_mvc.model.entity.Card;
import org.example.spring_mvc.model.response.CardResponse;
import org.example.spring_mvc.repository.CardRepository;
import org.example.spring_mvc.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public Page<CardResponse> list(int page, int size, Sort.Direction direction) {
        PageRequest pageable = PageRequest.of(page - 1, size, Sort.by(direction, "id"));

        return cardRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(Card::toResponse);
    }
}
