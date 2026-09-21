package net.skhu.dto;

import lombok.Data;

@Data // lombok이 메소드 자동생성
public class Student {
    String studentNo;
    String name;
    int departmentId;
    String gender;
    boolean absense;
    int year;
}
