package org.example.spring_mvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_mvc.common.response.PaginationResponse;
import org.example.spring_mvc.common.response.SuccessResponse;
import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.response.TeacherResponse;
import org.example.spring_mvc.service.TeacherService;
import org.example.spring_mvc.util.APIResponseUtil;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<PaginationResponse<List<TeacherResponse>>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String name
    ) {
        // Service returns Page<TeacherResponse> → wrap in ResponseEntity.ok()
        return ResponseEntity.ok(APIResponseUtil.pagination(HttpStatus.OK, teacherService.list(page, size, direction, name)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<TeacherResponse>> create(@Valid @RequestBody TeacherRequest request) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.CREATED, teacherService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<TeacherResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.OK, teacherService.get(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<TeacherResponse>> update(@PathVariable Long id,@Valid  @RequestBody TeacherRequest request) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.OK, teacherService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.NO_CONTENT));
    }
}
