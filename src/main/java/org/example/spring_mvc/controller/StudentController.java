package org.example.spring_mvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.spring_mvc.common.response.PaginationResponse;
import org.example.spring_mvc.common.response.SuccessResponse;
import org.example.spring_mvc.model.response.StudentResponse;
import org.example.spring_mvc.repository.StudentRepository;
import org.example.spring_mvc.model.request.StudentRequest;
import org.example.spring_mvc.service.StudentService;
import org.example.spring_mvc.util.APIResponseUtil;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // Recommend to use
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository repository;

    @GetMapping
    public ResponseEntity<PaginationResponse<List<StudentResponse>>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(APIResponseUtil.pagination(HttpStatus.OK, studentService.list(page,size,direction,name)));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<StudentResponse>> create(@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.CREATED, studentService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<StudentResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.OK, studentService.get(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<StudentResponse>> update(@PathVariable Long id,@Valid @RequestBody StudentRequest request) {
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.OK,studentService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.ok(APIResponseUtil.success(HttpStatus.NO_CONTENT));
    }

}
