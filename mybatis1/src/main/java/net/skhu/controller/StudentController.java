package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;

@Controller
public class StudentController {

    @Autowired StudentMapper studentMapper;

    @GetMapping("student/list")
    public String list(Model model) {
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }
}
