package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired StudentMapper studentMapper;

    @GetMapping("list1")
    public String list1(Model model) {
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students", students);
        return "student/list1";
    }

    @GetMapping("list2")
    public String list2(Model model,
            @RequestParam(value="name", defaultValue="") String name) {
        List<Student> students = studentMapper.findByName(name + "%");
        model.addAttribute("students", students);
        return "student/list2";
    }
}
