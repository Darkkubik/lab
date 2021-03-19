package com.example.demo.controller;

import com.example.demo.dao.Study_GroupJdbc;
import com.example.demo.model.Study_Group;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @CrossOrigin
public class Study_GroupController
{
    private final Study_GroupJdbc study_groupJdbc;

    public Study_GroupController(Study_GroupJdbc study_groupJdbc) {this.study_groupJdbc = study_groupJdbc;}

    // ПОЛУЧИТЬ ГРУППУ
    @GetMapping("/group/{id}")
    public Study_Group get_group (@PathVariable int id)
    {
        Study_Group group = study_groupJdbc.get_group(id);
        return group;
    }

    // ДОБАВИТЬ И ОБНОВИТЬ ГРУППУ
    @PostMapping("/group")
    public int set_or_update_group(@RequestBody Study_Group group)
    {
        return study_groupJdbc.set(group.getId(), group.getName());
    }

    // ПОКАЗАТЬ ВСЕ  ГРУППЫ
    @GetMapping("/group/all")
    public List<Study_Group> get_all_group ()
    {
        List<Study_Group> groups = study_groupJdbc.get_all();
        return groups;
    }

    // УДАЛИТЬ ГРУППУ
    @GetMapping("/group/delete/{id}")
    public int delete_group(@PathVariable int id)
    {
        int group = study_groupJdbc.delete(id);
        return group;
    }
}
