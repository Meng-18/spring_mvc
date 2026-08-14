package org.example.spring_mvc.service.impl;

import org.example.spring_mvc.model.entity.Student;
import org.example.spring_mvc.model.request.StudentRequest;
import org.example.spring_mvc.model.respone.StudentResponse;
import org.example.spring_mvc.repository.StudentRepository;
import org.example.spring_mvc.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<StudentResponse> list(int page, int size, Sort.Direction direction, String name){
        Sort sort = Sort.by(direction,"id");
        Pageable pageable = PageRequest.of(page -1, size, sort);
        return studentRepository.searchStudentByNameContainingIgnoreCase(name, pageable).map(Student::toResponse);
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        return studentRepository.save(request.toEntity()).toResponse();
    }

    @Override
    public StudentResponse get(Long id) {
        return studentRepository.findById(id)
                .map(Student::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found"));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found"));
        student.setName(request.getName());
        student.setGmail(request.getGmail());
        student.setAddress(request.getAddress());
        return studentRepository.save(student).toResponse();
    }

    @Override
    public void delete(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found"));
        studentRepository.delete(student);
    }

}