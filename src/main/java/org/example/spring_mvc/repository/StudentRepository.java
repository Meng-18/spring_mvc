package org.example.spring_mvc.repository;

import org.example.spring_mvc.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "SELECT t FROM Student t WHERE :name is null or :name = '' or lower(t.name) like lower(concat('%', :name, '%'))")
    Page<Student> searchStudentByNameContainingIgnoreCase(String name, Pageable pageable);
}
