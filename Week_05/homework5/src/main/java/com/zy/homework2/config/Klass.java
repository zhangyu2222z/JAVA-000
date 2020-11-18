package com.zy.homework2.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Configuration
public class Klass {
    
    List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void dong(){
        System.out.println(this.getStudents().size());
    }
    
}
