package com.syntaxsnipers.test.External;

import com.syntaxsnipers.test.Domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
   Optional<Student> findByName(String name);
   Optional<Student> findByNameAndAddress(String name, String Address);
}
