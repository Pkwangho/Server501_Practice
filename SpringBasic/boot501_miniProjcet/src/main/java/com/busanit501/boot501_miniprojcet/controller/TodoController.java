package com.busanit501.boot501_miniprojcet.controller;

import com.busanit501.boot501_miniprojcet.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller // 1)화면 제공 2) 데이터 제공
@Log4j2
public class TodoController {

    @GetMapping("/todo/list")
    public void list(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

    @GetMapping("/todo/register")
    public void register(Model model) {
        List<String> list = Arrays.asList("d", "e", "f");
        model.addAttribute("list", list);
    }


}
