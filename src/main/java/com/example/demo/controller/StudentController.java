package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController
{
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc) {this.studentJdbc = studentJdbc;}

    // ПОЛУЧИТЬ СТУДЕНТА
    @GetMapping("/student/{id}")
    public Student get_student (@PathVariable int id)
    {
        Student student = studentJdbc.get_student(id);
        return student;
    }

    // ДОБАВИТЬ И ОБНОВИТЬ СТУДЕНТА
    @PostMapping("/student")
    public int set_or_update_student(@RequestBody Student student)
    {
       return studentJdbc.set(student.getId(), student.getSurname(), student.getName(), student.getSecond_name(), student.getStudy_group_id());
    }

    // СПИСОК СТУДЕНТОВ
    @GetMapping("/student/all")
    public List<Student> get_all_student ()
    {
        List<Student> students = studentJdbc.get_all();
        return students;
    }

    // ПОЛУЧИТЬ СТУДЕНТОВ ПО ГРУППЕ
    @GetMapping("/student/group/{id}")
    public List<Student> get_all_student_group (@PathVariable int id)
    {
        List<Student> students = studentJdbc.get_all_group(id);
        return students;
    }

    // УДАЛИТЬ СТУДЕНТА
    @GetMapping("/student/delete/{id}")
    public int delete_student (@PathVariable int id)
    {
        int student = studentJdbc.delete(id);
        return student;
    }
}
