package me.chulhyeon.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        log.info("/thymeleaf/example.html Call");
        Person person = new Person();
        person.setId(1L);
        person.setAge(20);
        person.setName("박종휘");
        person.setHobbies(List.of("독서","운동"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDate.now());
        return "example";
    }

    @Setter @Getter
    static class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
