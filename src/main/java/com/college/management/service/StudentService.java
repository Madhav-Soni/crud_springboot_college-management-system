package com.college.management.service;

import com.college.management.entity.Student;
import com.college.management.entity.Teacher;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.StudentRepository;
import com.college.management.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public Student createStudent(Student student) {
        if (student.getTeacher() != null && student.getTeacher().getId() != null) {
            Long teacherId = student.getTeacher().getId();
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + teacherId));
            student.setTeacher(teacher);
        } else {
            student.setTeacher(null);
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + id));
    }

    public List<Student> getStudentsByBranch(String branch) {
        return studentRepository.findByBranchIgnoreCase(branch);
    }

    public Student updateStudent(Long id, Student details) {
        Student student = getStudentById(id);
        student.setName(details.getName());
        student.setBranch(details.getBranch());
        student.setEmail(details.getEmail());

        if (details.getTeacher() != null && details.getTeacher().getId() != null) {
            Long teacherId = details.getTeacher().getId();
            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + teacherId));
            student.setTeacher(teacher);
        } else {
            student.setTeacher(null);
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
