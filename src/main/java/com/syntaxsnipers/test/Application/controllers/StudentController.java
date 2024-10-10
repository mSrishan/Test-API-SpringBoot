package com.syntaxsnipers.test.Application.controllers;

import com.syntaxsnipers.test.Application.controllers.dto.request.CreateStudentDto;
import com.syntaxsnipers.test.Application.controllers.dto.response.StudentGeneralDto;
import com.syntaxsnipers.test.Domain.entity.Student;
import com.syntaxsnipers.test.Domain.entity.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

//    @GetMapping("/list")
//    public List<Student> getStudent() {
//        return studentRepository.findAll();
//    }
//    @GetMapping("/get")
//    public Student getStudentById(){
//         Optional<Student> student =studentRepository.findByName("Srishan");
//         if(student.isPresent()){
//             return student.get();
//         }else {
//             return new Student();
//         }
//
//
//    }
//    @GetMapping("/findNameAndAddress")
//    public Student getStudentByNameAndAddress() {
//        Optional<Student> student = studentRepository.findByNameAndAddress("Mandawala", "Rikillagaskada");
//        if(student.isPresent()){
//            return student.get();
//        } else {
//            return  new Student();
//        }
//    }}


//  @PostMapping("/add")
//  public String addData(){
//        return "Success";
//  }
//}
   @GetMapping("/getData")
    public ResponseEntity<StudentGeneralDto> getData(@RequestParam Integer id) {
        return  studentService.getData(id);

    }

    @PostMapping("/addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody CreateStudentDto createStudentDto){
       return studentService.addStudent(createStudentDto);
    }

    @DeleteMapping("/deleteStudent")
    public Object deleteStudent(@RequestParam Integer id){
       return studentService.deleteStudent(id);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestParam Integer id,@RequestParam String newName){
       return studentService.updateStudent(id, newName);

    }
}