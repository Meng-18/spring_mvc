package org.example.spring_mvc.controller;

import org.example.spring_mvc.model.respone.StudentResponse;
import org.example.spring_mvc.repository.StudentRepository;
import org.example.spring_mvc.model.request.StudentRequest;
import org.example.spring_mvc.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentControllers {
    private final StudentService studentService;
    private final StudentRepository repository;

    public StudentController(StudentRepository repository, StudentService studentService) {
        this.studentService = studentService;
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(studentService.list(page, size, direction, name));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@RequestBody StudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id, @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
