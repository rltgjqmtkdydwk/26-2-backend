package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.skhu.dto.Student;

@Mapper
public interface StudentMapper {

    @Select("""
        SELECT s.*, d.name departmentName
        FROM student s JOIN department d ON s.departmentId = d.id
        WHERE s.name LIKE #{name} """)
    List<Student> findByName(String name);

    @Select("SELECT * FROM student WHERE id=#{id}")
    Student findById(int id);

    @Update("""
       UPDATE student
       SET
         studentNo = #{studentNo},
         name = #{name},
         departmentId = #{departmentId},
         sex = #{sex},
         phone = #{phone},
         email = #{email}
       WHERE id = #{id} """)
    void update(Student student);
}
