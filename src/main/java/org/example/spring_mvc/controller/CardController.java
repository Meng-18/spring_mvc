package org.example.spring_mvc.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.spring_mvc.common.response.PaginationResponse;
import org.example.spring_mvc.model.response.CardResponse;
import org.example.spring_mvc.service.CardService;
import org.example.spring_mvc.util.APIResponseUtil;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<PaginationResponse<List<CardResponse>>> list(
            @RequestParam(defaultValue = "1") @Min(1) int page,
            @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size,
            @RequestParam(defaultValue = "ACS") Sort.Direction direction
    ) {
        return ResponseEntity.ok(APIResponseUtil.pagination(HttpStatus.OK, cardService.list(page,size,direction)));
    }


}
