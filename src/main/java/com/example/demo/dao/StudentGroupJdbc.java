package com.example.demo.dao;
import com.example.demo.model.Student;
import com.example.demo.model.StudentGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public class StudentGroupJdbc {
    private final JdbcTemplate jdbcTemplate;
    public StudentGroupJdbc(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

// Просмотр группы
    public StudentGroup get(int id){
        String sql = "SELECT * FROM STUDY_GROUP WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapStudentGroup, id);
    }

// Просмотр всех групп
    public List<StudentGroup> getAll(){
        String sql = "SELECT * FROM STUDY_GROUP";
        return jdbcTemplate.query(sql, this::mapStudentGroup);

    }

    private StudentGroup mapStudentGroup(ResultSet rs, int i) throws SQLException{
        return new StudentGroup(
                rs.getInt("id"),
                rs.getString("name")
        );

    }

// Редактирование группы

    public void UpdateStudentGroup(int id, String name) {

        this.jdbcTemplate.update(
                "MERGE INTO STUDY_GROUP KEY (ID) VALUES (?, ?)", id, name);

    }

    public void UpdateStudentGroup(StudentGroup student_group) {
        int status = jdbcTemplate.update(
                "INSERT INTO STUDY_GROUP VALUES(?, ?)", student_group.getId(), student_group.getName());
        if(status == 0){
            CreateStudentGroup(student_group);
        }

    }

// Создание группы

    public void CreateStudentGroup(int id, StudentGroup name) {

        this.jdbcTemplate.update(
                "INSERT INTO STUDY_GROUP VALUES(?, ?)",
                id, name);
    }

    public void CreateStudentGroup(StudentGroup student_group) {
        this.jdbcTemplate.update(
                "INSERT INTO STUDY_GROUP VALUES(?, ?)",
                student_group.getId(), student_group.getName());
    }

// Удаление студента

    public int delete(String id) {
        return jdbcTemplate.update("DELETE FROM STUDY_GROUP WHERE id = ?", id);
    }

}