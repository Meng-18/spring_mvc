package org.example.spring_mvc.controller;

import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.respone.TeacherResponse;
import org.example.spring_mvc.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherRepository repository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.repository = teacherRepository;
    }

    @GetMapping
    public Page<Teacher> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
//            @RequestParam String name
    ) {
        Sort sort = Sort.by("id").ascending().and(Sort.by("name").ascending());
        PageRequest pageable = PageRequest.of(page - 1, size, sort);
        return repository.findAll(pageable);
    }


    @GetMapping("/{id}")
    public Teacher get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found"));
    }


    @PostMapping
    public Teacher create(@RequestBody TeacherRequest request) {
        return repository.save(request.toEntity());
    }


    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data Not Found"));
        return repository.save(teacher.toUpdate(request));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
