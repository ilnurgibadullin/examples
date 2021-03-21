package ru.gibadullin.springrest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gibadullin.springrest.entity.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> listStudents;

    @PostConstruct
    public void loadData() {
        listStudents = new ArrayList<>();
        listStudents.add(new Student("Vladimir", "Vladimirov"));
        listStudents.add(new Student("Alexander", "Alexandrov"));
        listStudents.add(new Student("Ivan", "Ivanov"));
    }

    @GetMapping("/students")
    public List<Student> getListStudents() {
        return listStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if ((studentId >= listStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found " + studentId);
        }

        return listStudents.get(studentId);
    }
}
