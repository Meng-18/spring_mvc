package org.example.spring_mvc.service;


import org.example.spring_mvc.model.request.StudentRequest;
import org.example.spring_mvc.model.respone.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface StudentService {
    Page<StudentResponse> list(int page, int size, Sort.Direction direction, String name);

    StudentResponse get(Long id);

    StudentResponse create(StudentRequest request);

    StudentResponse update(Long id, StudentRequest request);

    void delete(long id);
}
