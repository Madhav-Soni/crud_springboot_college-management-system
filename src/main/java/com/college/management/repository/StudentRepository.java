package com.college.management.repository;

import com.college.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByBranchIgnoreCase(String branch);
}
