package com.example.demo.controller;

import com.example.demo.dao.JournalJdbc;
import com.example.demo.model.Journal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin @RestController
public class JournalController
{
    private final JournalJdbc journalJdbc;

    public JournalController(JournalJdbc journalJdbc) {this.journalJdbc = journalJdbc;}


    // ОБНОВИТЬ ЖУРНАЛ
    @PostMapping("/journal")
    public int set_or_update_student(@RequestBody Journal journal)
    {
        return journalJdbc.set(journal.getId(), journal.getStudent_id(), journal.getStudy_plan_id(), journal.getIn_time(),
                journal.getCount(), journal.getMark_id());
    }

    // ПРОСМОТР ЖУРНАЛА
    @GetMapping("/journal/all")
    public List<Journal> get_all ()
    {
        List<Journal> journals = journalJdbc.get_all();
        return journals;
    }

    // ПРОСМОТР ЖУРНАЛА ORDER
    @GetMapping("/journal/all/order")
    public List<Journal> get_all_order ()
    {
        List<Journal> journals = journalJdbc.get_all_order();
        return journals;
    }

    // ПРОСМОТР ЖУРНАЛА ПО СТУДЕНТУ
    @GetMapping("/journal/student/{id}")
    public List<Journal> get_all_student (@PathVariable int id)
    {
        List<Journal> journals = journalJdbc.get_all_group(id);
        return journals;
    }

    // ПРОСМОТР ЖУРНАЛА ПО ГРУППЕ
    @GetMapping("/journal/group/{id}")
    public List<Journal> get_all_group (@PathVariable int id)
    {
        List<Journal> journals = journalJdbc.get_all_group(id);
        return journals;
    }
}
