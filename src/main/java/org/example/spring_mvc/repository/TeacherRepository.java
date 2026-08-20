package org.example.spring_mvc.repository;

import org.example.spring_mvc.model.entity.Student;
import org.example.spring_mvc.model.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    @Query("select t from Teacher t where :name is null or :name='' or lower(t.name) like lower(concat('%',:name,'%'))")
    Page<Teacher> searchTeacherByNameContainingIgnoreCase(String name, Pageable pageable);
}
