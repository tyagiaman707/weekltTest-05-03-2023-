package com.geekster.weeklyTest.service;

import com.geekster.weeklyTest.dao.StudentRepository;
import com.geekster.weeklyTest.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return null;
        }
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setAge(student.getAge());
        existingStudent.setActive(student.getActive());
        existingStudent.setAdmissionDate(student.getAdmissionDate());
        return studentRepository.save(existingStudent);
    }

    public boolean deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent == null) {
            return false;
        }
        studentRepository.delete(existingStudent);
        return true;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getStudentsByAgeGreaterThan(Integer age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    public List<Student> getActiveStudents() {
        return studentRepository.findByActive();
    }
    public List<Student> getInactiveStudents() {
        return studentRepository.findByActiveFalse();
    }
    public List<Student> findByLastNameAndFirstName(String lastName, String firstName) {
        return studentRepository.findByLastNameAndFirstName(lastName, firstName);

    }
    public List<Student> findByAgeOrderByLastnameDesc(Integer age) {
        return studentRepository.findByAgeOrderByLastnameDesc(age);
    }
    public List<Student> getStudentsByAdmissionDateBetween(Date startDate, Date endDate) {
        return studentRepository.findByAdmissionDateBetween(startDate, endDate);
    }
}
