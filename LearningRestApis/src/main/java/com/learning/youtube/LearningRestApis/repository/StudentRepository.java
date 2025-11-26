package com.learning.youtube.LearningRestApis.repository;

import com.learning.youtube.LearningRestApis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
