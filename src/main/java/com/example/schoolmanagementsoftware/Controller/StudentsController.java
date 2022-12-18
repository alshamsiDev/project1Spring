package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Model.Student;
import com.example.schoolmanagementsoftware.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StudentsController {
    /*
     * Get all students
     */
    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity getStudents() {
        return ResponseEntity.status(200).body(studentService.getAll());
    }


    /**
     * Create endpoint that takes student id and return one student.
     */
    @GetMapping("/students/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id) {
        return studentService.findOrFail(id);
    }

    /**
     * Add new student
     *
     * @param student
     * @param errors
     * @return
     */

    @PostMapping("/students")
    public ResponseEntity createStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(400).body(errorMessage);
        }
        return studentService.create(student);
    }

    /**
     * Update Student
     *
     * @param student
     * @param errors
     * @return
     */
    @PutMapping("/students/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(400).body(errorMessage);
        }
        return studentService.update(id, student);
    }

    /**
     * delete Student
     *
     * @param id
     * @return
     */

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity destroyStudent(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    /**
     * Create endpoint that takes student name and return one student .
     *
     * @param name
     * @return
     */
    @GetMapping("/students/search")
    public ResponseEntity searchStudentByName(@RequestParam String name) {
        return studentService.search(name);
    }

    /**
     * Create endpoint that takes student age and return all students who have this age or above.
     *
     * @param age
     * @return
     */
    @GetMapping("/students/fetchStudentByAge")
    public ResponseEntity searchStudentsByAge(@RequestParam Integer age) {
        return studentService.searchByAge(age);
    }

    /**
     * Create endpoint
     * @param major
     * @return
     */
    @GetMapping("/students/fetchStudentsByMajor")
    public ResponseEntity searchStudentsByMajor(@RequestParam String major) {
        return studentService.searchByMajor(major);
    }
}
