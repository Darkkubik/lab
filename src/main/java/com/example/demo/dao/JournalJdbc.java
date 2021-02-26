package com.example.demo.dao;

import com.example.demo.model.Journal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository

public class JournalJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public JournalJdbc(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private Journal mapJournal(ResultSet rs, int i) throws SQLException
    {
        Journal journal= new Journal(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
        return journal;
    }

    // ОБНОВЛЕНИЕ ЖУРНАЛА
    public int set(int id, int student_id, int study_plan_id, boolean int_time, int count, int mark_id )
    {
        return jdbcTemplate.update("MERGE INTO JOURNAL (ID, STUDENT_ID, STUDY_PLAN_ID, IN_TIME, COUNT, MARK_ID)" +
                "VALUES (?, ?, ?, ?, ?, ?)", id, student_id, study_plan_id, int_time, count, mark_id);
    }

    // ПРОСМОТР ЖУРНАЛА
    public List<Journal> get_all()
    {
        return jdbcTemplate.query("SELECT * FROM JOURNAL", this::mapJournal);
    }

    // ПРОСМОТР ЖУРНАЛА ПО СТУДЕНТУ
    public List<Journal> get_all_stud(int id)
    {
        return jdbcTemplate.query("SELECT * FROM JOURNAL WHERE student_id = ?", this::mapJournal, id);
    }

    // ПРОСМОТР ЖУРНАЛА ПО ГРУППЕ
    public List<Journal> get_all_group(int id)
    {
        return jdbcTemplate.query("SELECT * FROM JOURNAL INNER JOIN STUDENT st ON st.id = JOURNAL.student_id where study_group_id = ?", this::mapJournal, id);
    }
}
