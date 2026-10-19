package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.skhu.dto.Student;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Autowired StudentMapper studentMapper;

    @GetMapping("list")
    public String list(Model model,
            @RequestParam(value="name", defaultValue="") String name) {
        List<Student> students = studentMapper.findByName(name + "%");
        model.addAttribute("students", students);
        model.addAttribute("name", name);
        return "student/list";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id) {
        Student student = studentMapper.findById(id);
        StudentEdit studentEdit = new StudentEdit();
        studentEdit.setId(student.getId());
        studentEdit.setStudentNo(student.getStudentNo());
        studentEdit.setDepartmentId(student.getDepartmentId());
        studentEdit.setName(student.getName());
        studentEdit.setPhone(student.getPhone());
        studentEdit.setEmail(student.getEmail());
        studentEdit.setSex(student.getSex());
        model.addAttribute("studentEdit", studentEdit);
        return "student/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, @Valid StudentEdit student, BindingResult bindingResult) {
        try {
            log.debug(student.toString());
            if (bindingResult.hasErrors()) {
				throw new Exception("저장할 수 없습니다");
			}

            // student 객체를 DB에 저장하는 구현 생략
            return "redirect:list";
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            return "student/edit";
        }
    }
}

