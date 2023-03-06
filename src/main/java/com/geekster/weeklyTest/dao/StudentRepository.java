package com.geekster.weeklyTest.dao;

import com.geekster.weeklyTest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Student findStudentById(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Student s WHERE s.id = :id")
    void deleteStudentById(@Param("id") Long id);

    @Query("SELECT s FROM Student s WHERE s.lastName = :lastName AND s.firstName = :firstName")
    List<Student> findByLastNameAndFirstName(@Param("lastName") String lastName, @Param("firstName") String firstName);

    @Query("SELECT s FROM Student s WHERE s.firstName = :firstName")
    List<Student> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findByAgeGreaterThan(@Param("age") Integer age);

    @Query("SELECT s FROM Student s WHERE s.active = true")
    List<Student> findByActive();

    @Query("SELECT s FROM Student s WHERE s.active = false")
    List<Student> findByActiveFalse();

    @Query("SELECT s FROM Student s WHERE s.admissionDate BETWEEN :startDate AND :endDate")
    List<Student> findByAdmissionDateBetween(@Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate);
    @Query("SELECT s FROM Student s WHERE s.age = :age ORDER BY s.lastName DESC")
    List<Student> findByAgeOrderByLastnameDesc(@Param("age") Integer age);
}
