package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teacherArrayList = new ArrayList<>();


    public ArrayList<Teacher> getAll() {
        return teacherArrayList;
    }

    public ResponseEntity findOrFail(Integer id) {
        for (Teacher teacher : teacherArrayList) {
            if (teacher.getId().equals(id)) {
                return ResponseEntity.status(200).body(teacher);
            }
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity create(Teacher teacher) {
        try {
            teacherArrayList.add(teacher);
            return ResponseEntity.status(201).body("Teacher has been created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error Occur");
        }
    }

    public ResponseEntity update(Integer id, Teacher teacher) {
        for (int i = 0; i < teacherArrayList.size(); i++) {
            if (teacherArrayList.get(i).getId().equals(id)) {
                teacherArrayList.set(i, teacher);
                return ResponseEntity.status(200).body("Teacher updated successful");
            }
        }
        return ResponseEntity.status(400).body("Eroor occur");
    }

    public ResponseEntity delete(Integer id) {
        for (int i = 0; i < teacherArrayList.size(); i++) {
            if (teacherArrayList.get(i).getId().equals(id)) {
                teacherArrayList.remove(i);
                return ResponseEntity.status(200).body("Teacher deleted from our records successful");
            }
        }
        return ResponseEntity.status(400).body("Error occur");
    }

    public ResponseEntity searchBySalary(Float salary) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : teacherArrayList) {
            if (teacher.getSalary() >= salary) {
                teachers.add(teacher);
            }
        }
        return ResponseEntity.status(200).body(teachers);
    }
}
