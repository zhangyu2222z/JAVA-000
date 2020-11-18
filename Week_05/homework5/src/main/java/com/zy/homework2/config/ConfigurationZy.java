package com.zy.homework2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangy
 * @version 3.0
 * @description: 配置项
 * @email: zhangy2222z@sina.cn
 * @create 2020-11-18 8:33
 **/

@Configuration
//@EnableConfigurationProperties
//@ConditionalOnProperty(prefix = "filter", name="loginFilter", havingValue = "true")
//@ConditionalOnProperty(value = "filter.loginFilter")
public class ConfigurationZy {

    @Bean(name = "student123")
    public Student getStudentOne(){
        Student student = new Student(123, "KK123");
        return student;
    }

    @Bean(name = "student100")
    public Student getStudentTwo(){
        Student student = new Student(100, "KK100");
        return student;
    }

    @Bean(name = "klass")
    public Klass getKlass(){
        Klass klass = new Klass();
        List<Student> students = new ArrayList<>();
        Student student1 = getStudentOne();
        Student student2 = getStudentTwo();
        students.add(student1);
        students.add(student2);
        klass.setStudents(students);
        return klass;
    }

    @Bean(name = "school")
    public School getSchool(){
        return new School();
    }


}
