package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Mark;
import com.example.demo.model.Student;
import com.example.demo.dao.MarkJdbc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController
{
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {this.studentJdbc = studentJdbc;}

    @GetMapping("/student/{id}")
    public Student getstudent (@PathVariable int id)
    {
        Student student = studentJdbc.get(id);
        return student;
    }

    @PostMapping("/student")
    public Student setstudent(@PathVariable Student student)
    {
       return studentJdbc.set(student.getId(), student.getSurname(), student.getName(), student.getSecond_name(), student.getStudy_group_id());
    }
}
