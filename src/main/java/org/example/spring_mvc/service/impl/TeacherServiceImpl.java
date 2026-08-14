package org.example.spring_mvc.service.impl;

import org.example.spring_mvc.model.entity.Teacher;
import org.example.spring_mvc.model.request.TeacherRequest;
import org.example.spring_mvc.model.respone.TeacherResponse;
import org.example.spring_mvc.repository.TeacherRepository;
import org.example.spring_mvc.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;


    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public Page<TeacherResponse> list(int page, int size, Sort.Direction direction, String name) {
        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(page -1, size, sort);
        return teacherRepository.searchTeacherByNameContainingIgnoreCase(name, pageable).map(Teacher::toResponse);
    }

    @Override
    public TeacherResponse create(TeacherRequest request) {
        return teacherRepository.save(request.toEntity()).toResponse();
    }

    @Override
    public TeacherResponse get(Long id) {
        return teacherRepository.findById(id)
                .map(Teacher::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
    }

    @Override
    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
        teacher.setName(request.getName());
        teacher.setEmail(request.getEmail());
        teacher.setAddress(request.getAddress());
        return teacherRepository.save(teacher).toResponse();
    }

    @Override
    public void delete(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Teacher not found"));
        teacherRepository.delete(teacher);
    }
}
