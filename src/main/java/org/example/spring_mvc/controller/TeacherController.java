package org.example.spring_mvc.controller;

import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.respone.TeacherResponse;
import org.example.spring_mvc.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<Page<TeacherResponse>> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String name
    ) {
        // Service returns Page<TeacherResponse> → wrap in ResponseEntity.ok()
        return ResponseEntity.ok(teacherService.list(page, size, direction, name));
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> create(@RequestBody TeacherRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(teacherService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.get(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return ResponseEntity.ok(teacherService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
