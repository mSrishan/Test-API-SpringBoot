package com.syntaxsnipers.test.Domain.entity.service;

import com.syntaxsnipers.test.Application.controllers.dto.request.CreateStudentDto;
import com.syntaxsnipers.test.Application.controllers.dto.response.StudentGeneralDto;
import com.syntaxsnipers.test.Domain.entity.Student;
import com.syntaxsnipers.test.External.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    // Fetches student data by ID and maps it to a StudentGeneralDto
    public ResponseEntity<StudentGeneralDto> getData(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        // If student is found, map its data to StudentGeneralDto and return
        return optionalStudent.map(student -> {
            StudentGeneralDto studentGeneralDto = new StudentGeneralDto();
            studentGeneralDto.setId(student.getId());
            studentGeneralDto.setName(student.getName());
            studentGeneralDto.setGender(student.getGender());
            return ResponseEntity.ok(studentGeneralDto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Adds a new student to the repository and returns the created student with status 201
    public ResponseEntity<Student> addStudent(CreateStudentDto createStudentDto) {
        // Map CreateStudentDto to Student entity
        Student student = new Student();
        student.setName(createStudentDto.getName());
        student.setGender(createStudentDto.getGender());
        student.setAddress(createStudentDto.getAddress());
        student.setAge(createStudentDto.getAge());

        // Save the new student to the repository
        Student savedStudent = studentRepository.save(student);

        // Return the saved student with status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    public Object deleteStudent(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.delete(optionalStudent.get());
            return ResponseEntity.ok("Deleted.");
        }else {
            return ResponseEntity.notFound();
        }
    }

    public ResponseEntity<String> updateStudent(Integer id, String newName) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            log.info("Student found, id is :"+ id);
            Student student = optionalStudent.get();
            student.setName(newName);
            studentRepository.save(student);
            return ResponseEntity.ok("Updated.");
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
