package com.college.management.service;

import com.college.management.entity.Teacher;
import com.college.management.exception.ResourceNotFoundException;
import com.college.management.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found: " + id));
    }

    public List<Teacher> getTeachersByDepartment(String department) {
        return teacherRepository.findByDepartmentIgnoreCase(department);
    }

    public Teacher updateTeacher(Long id, Teacher details) {
        Teacher teacher = getTeacherById(id);
        teacher.setName(details.getName());
        teacher.setDepartment(details.getDepartment());
        teacher.setEmail(details.getEmail());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        Teacher teacher = getTeacherById(id);
        teacherRepository.delete(teacher);
    }
}
