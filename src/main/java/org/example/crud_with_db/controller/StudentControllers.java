package org.example.crud_with_db.controller;

import org.example.crud_with_db.Student;
import org.example.crud_with_db.repository.StudentRepository;
import org.example.crud_with_db.model.request.StudentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentControllers {
    private final StudentRepository repository;

    public StudentControllers(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> list() {
        return repository.findAll();
    }

    @PostMapping
    public Student create(@RequestBody StudentRequest request) {
//        Student student = new Student();
//        student.setName(request.getName());
//        student.setGmail(request.getGmail());
//        student.setAddress(request.getAddress());

        return repository.save(request.toEntity());
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody StudentRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found"));

        student.setName(request.getName());
        student.setGmail(request.getGmail());
        student.setAddress(request.getAddress());

        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(student);
    }

}
