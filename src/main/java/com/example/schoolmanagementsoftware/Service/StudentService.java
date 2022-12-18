package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class StudentService {
    ArrayList<Student> studentArrayList = new ArrayList<>();


    public ArrayList<Student> getAll() {
        return studentArrayList;
    }

    public ResponseEntity findOrFail(Integer id) {
        for (Student student : studentArrayList) {
            if (student.getId().equals(id)) {
                return ResponseEntity.status(200).body(student);
            }
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity create(Student student) {
        try {
            studentArrayList.add(student);
            return ResponseEntity.status(201).body("Student has been created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error Occur");
        }
    }

    public ResponseEntity update(Integer id, Student student) {
        for (int i = 0; i < studentArrayList.size(); i++) {
            if (studentArrayList.get(i).getId().equals(id)) {
                studentArrayList.set(i, student);
                return ResponseEntity.status(200).body("Student updated successful");
            }
        }
        return ResponseEntity.status(400).body("Eroor occur");
    }

    public ResponseEntity delete(Integer id) {
        for (int i = 0; i < studentArrayList.size(); i++) {
            if (studentArrayList.get(i).getId().equals(id)) {
                studentArrayList.remove(i);
                return ResponseEntity.status(200).body("Student deleted from our records successful");
            }
        }
        return ResponseEntity.status(400).body("Error occur");
    }

    public ResponseEntity search(String name) {
        for (Student student : studentArrayList) {
            if (student.getName().equalsIgnoreCase(name.replaceAll("\"", ""))) {
                return ResponseEntity.status(200).body(student);
            }
        }
        return ResponseEntity.status(400).body("Error occur");
    }

    public ResponseEntity searchByAge(Integer age) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : studentArrayList) {
            if (student.getAge() >= age) {
                students.add(student);
            }
        }
        return ResponseEntity.status(200).body(students);
    }

    public ResponseEntity searchByMajor(String major) {
        ArrayList<Student> students = new ArrayList<>();
        for (Student student : studentArrayList) {
            if (student.getMajor().equalsIgnoreCase(major.replaceAll("\"", ""))) {
                students.add(student);
            }
        }
        return ResponseEntity.status(200).body(students);
    }
}
