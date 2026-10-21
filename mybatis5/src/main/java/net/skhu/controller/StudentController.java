package net.skhu.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
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
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.StudentMapper;
import net.skhu.model.StudentEdit;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

    @Autowired StudentMapper studentMapper;
    @Autowired DepartmentMapper departmentMapper;
    ModelMapper modelMapper = new ModelMapper();

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
        StudentEdit studentEdit = modelMapper.map(student, StudentEdit.class);
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departmentMapper.findAll());
        return "student/edit";
    }

    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model, @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            log.debug(studentEdit.toString());
            if (bindingResult.hasErrors()) {
				throw new Exception("저장할 수 없습니다");
			}

            Student student = modelMapper.map(studentEdit, Student.class);
            studentMapper.update(student);
            return "redirect:list";
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            model.addAttribute("departments", departmentMapper.findAll());
            return "student/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            studentMapper.deleteById(studentEdit.getId());
            return "redirect:list";
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            model.addAttribute("departments", departmentMapper.findAll());
            return "student/edit";
        }
    }

    @GetMapping("create")
    public String create(Model model) {
        StudentEdit studentEdit = new StudentEdit();
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departmentMapper.findAll());
        return "student/edit";
    }

    @PostMapping("create")
    public String create(Model model, @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            log.debug(studentEdit.toString());
            if (bindingResult.hasErrors()) {
				throw new Exception("저장할 수 없습니다");
			}

            Student student = modelMapper.map(studentEdit, Student.class);
            studentMapper.insert(student);
            return "redirect:list";
        }
        catch (Exception ex) {
            bindingResult.reject("", null, ex.getMessage());
            model.addAttribute("departments", departmentMapper.findAll());
            return "student/edit";
        }
    }
}
