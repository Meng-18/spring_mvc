package org.example.spring_mvc.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.example.spring_mvc.exception.CustomException;
import org.example.spring_mvc.model.entity.Student;
import org.example.spring_mvc.model.request.StudentRequest;
import org.example.spring_mvc.model.response.StudentResponse;
import org.example.spring_mvc.repository.StudentRepository;
import org.example.spring_mvc.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Page<StudentResponse> list(int page, int size, Sort.Direction direction, String name) {
        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return studentRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(name)) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.trim().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable).map(Student::toResponse);
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        String code = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        return studentRepository.save(request.toEntity(code)).toResponse();
    }

    @Override
    public StudentResponse get(Long id) {
        return studentRepository.findById(id)
                .map(Student::toResponse)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Student Not Found"));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Student Not Found"));
        student.setName(request.getName());
        student.setGmail(request.getEmail());
        student.setAddress(request.getAddress());
        student.getCard().setIssueDate(request.getCardRequest().getIssueDate());
        student.getCard().setExpiryDate(request.getCardRequest().getExpiryDate());
        return studentRepository.save(student).toResponse();
    }

    @Override
    public void delete(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Student Not Found"));
        studentRepository.delete(student);
    }

}