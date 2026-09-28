package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.skhu.model.StudentEdit;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

    @GetMapping("edit")
    public String edit(Model model) {
        StudentEdit student = new StudentEdit();
        student.setName("홍길동");
        log.debug(student.toString());
        model.addAttribute("studentEdit", student);
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

    @GetMapping("list")
    public String list() {
        // 학색 목록을 DB에서 조회해서 뷰에 전달하는 코드 생략
        return "student/list";
    }
}
