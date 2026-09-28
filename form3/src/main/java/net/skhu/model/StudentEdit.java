package net.skhu.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentEdit {
    int id;

    @NotEmpty @NotBlank
    @Size(min=8, max=12)
    String studentNo;

    @NotEmpty @NotBlank
    @Size(min=2, max=20)
    String name;

    @Min(1)
    int departmentId;

    @NotEmpty @NotBlank
    @Pattern(regexp="남자|여자")
    String gender;

    boolean absense;

    @Min(1) @Max(4)
    int year;
}
