package net.skhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.skhu.dto.Student;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

    @GetMapping("edit")
    public String edit(Model model) {
        Student student = new Student();
        student.setName("홍길동");
        log.debug(student.toString()); //디버깅
        model.addAttribute("student", student);
        return "student/edit";
    }

    @PostMapping("edit")
    public String edit(Model model, Student student) {
        try {
            log.debug(student.toString()); //디버깅
            if (StringUtils.hasText(student.getStudentNo()) == false) {
				throw new Exception("학번을 입력하세요");
			}
            if (StringUtils.hasText(student.getName()) == false) {
				throw new Exception("이름을 입력하세요");
			}

            // student 객체를 DB에 저장하는 구현 생략
            return "redirect:list";
        }
        catch (Exception ex) {
            model.addAttribute("errorMsg", ex.getMessage());
            return "student/edit";
        }
    }

    @GetMapping("list")
    public String list() {
        // 학 목록을 DB에서 조회해서 뷰에 전달하는 코드 생략
        return "student/list";
    }
}
