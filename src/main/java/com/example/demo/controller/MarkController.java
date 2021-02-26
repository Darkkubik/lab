package com.example.demo.controller;

import com.example.demo.model.Mark;
import com.example.demo.dao.MarkJdbc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkController
{
    private final MarkJdbc markjdbc;

    public MarkController(MarkJdbc markjdbc) {this.markjdbc = markjdbc;}

    @GetMapping("/mark/{id}")
    public Mark getmark (@PathVariable int id)
    {
        Mark mark = markjdbc.get_mark(id);
        return mark;
    }
}
