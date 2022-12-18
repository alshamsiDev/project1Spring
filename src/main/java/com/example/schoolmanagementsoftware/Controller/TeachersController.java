package com.example.schoolmanagementsoftware.Controller;

import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TeachersController {
    /*
     * Get all teachers
     */
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity getTeachers() {
        return ResponseEntity.status(200).body(teacherService.getAll());
    }


    /**
     * Create endpoint that takes teacher id and return one teacher.
     */
    @GetMapping("/teachers/{id}")
    public ResponseEntity getTeacher(@PathVariable Integer id) {
        return teacherService.findOrFail(id);
    }

    /**
     * Add new teacher
     *
     * @param teacher
     * @param errors
     * @return
     */

    @PostMapping("/teachers")
    public ResponseEntity createTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(400).body(errorMessage);
        }
        return teacherService.create(teacher);
    }

    /**
     * Update Teacher
     *
     * @param teacher
     * @param errors
     * @return
     */
    @PutMapping("/teachers/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id, @RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()).collect(Collectors.joining("; "));
            return ResponseEntity.status(400).body(errorMessage);
        }
        return teacherService.update(id, teacher);
    }

    /**
     * delete Teacher
     *
     * @param id
     * @return
     */

    @DeleteMapping("/teachers/delete/{id}")
    public ResponseEntity destroyTeacher(@PathVariable Integer id) {
        return teacherService.delete(id);
    }


    @GetMapping("/teachers/searchBySalary")
    public ResponseEntity searchTachersBySalary(@RequestParam Float salary) {
        return teacherService.searchBySalary(salary);
    }

}
