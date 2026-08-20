package org.example.spring_mvc.service;

import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.response.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface TeacherService {
    Page<TeacherResponse> list(int page, int size, Sort.Direction direction, String name);

    TeacherResponse create(TeacherRequest request);

    TeacherResponse get(Long id);

    TeacherResponse update(Long id, TeacherRequest request);

    void delete(Long id);
}
