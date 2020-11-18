package com.zy.homework2.config;


import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Configuration
public class Student implements Serializable {
    
    private int id;
    private String name;

    public Student () {

    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    public void init(){
//        System.out.println("hello...........");
//    }
    
//    public Student create(){
//        return new Student(101,"KK101");
//    }
}
