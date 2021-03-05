package com.example.demo.dao;

import com.example.demo.model.Study_Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Study_GroupJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public Study_GroupJdbc(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private Study_Group mapGroup (ResultSet rs, int i) throws SQLException
    {
        Study_Group group = new Study_Group(
                rs.getInt("id"),
                rs.getString("name")
        );
        return group;
    }

    // СОЗДАНИЕ И ОБНОВЛЕНИЕ СТУДЕНТА
    public int set(int id, String name)
    {
        return jdbcTemplate.update("MERGE INTO STUDY_GROUP (ID, NAME) VALUES (?, ?)", id, name);
    }

    // ПРОСМОТР СТУДЕНТА
    public Study_Group get_group(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDY_GROUP WHERE id = ?", this::mapGroup, id);
    }

    // ПРОСМОТР СТУДЕНТОВ
    public List<Study_Group> get_all()
    {
        return jdbcTemplate.query("SELECT * FROM STUDY_GROUP", this::mapGroup);
    }


    // УДАЛЕНИЕ
    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM STUDY_GROUP WHERE id = ?",  id);
    }
}
