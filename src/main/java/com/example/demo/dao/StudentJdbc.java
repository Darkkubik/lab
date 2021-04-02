package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class StudentJdbc {
    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Student mapStudent(ResultSet rs, int i) throws SQLException {
        Student student = new Student(
                rs.getInt("id"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("second_name"),
                rs.getInt("study_group_id")
        );
        return student;
    }

    public Student search(String student) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE id = ?", Student.class, student);
    }

    // СОЗДАНИЕ И ОБНОВЛЕНИЕ СТУДЕНТА
    public int set(int id, String surname, String name, String second_name, int study_group_id) {
        return jdbcTemplate.update("MERGE INTO STUDENT (ID, SURNAME, NAME, SECOND_NAME, STUDY_GROUP_ID)" +
                "VALUES (?, ?, ?, ?, ?)", id, surname, name, second_name, study_group_id);
    }

    // ПРОСМОТР СТУДЕНТА
    public Student get_student(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE id = ?", this::mapStudent, id);
    }

    // ПРОСМОТР СТУДЕНТОВ
    public List<Student> get_all() {
        return jdbcTemplate.query("SELECT * FROM STUDENT", this::mapStudent);
    }

    // ПРОСМОТР СТУДЕНТОВ ОРДЕР
    public List<Student> get_all_order() {
        return jdbcTemplate.query("SELECT * FROM STUDENT ORDER by surname", this::mapStudent);
    }

    // ПРОСМОТР СТУДЕНТОВ ПО ГРУППЕ
    public List<Student> get_all_group(int id) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE study_group_id = ?", this::mapStudent, id);
    }

    // УДАЛЕНИЕ
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM STUDENT WHERE id = ?", id);
    }

}
