package com.zy;

import com.zy.test.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//        1、
        context.getBean("user");
//        2、
        context.getBean(User.class);
//        3、
        context.getBean("user", User.class);
//        4、
        context.getBean(User.class, "zy");
//        5、
        context.getBean("user", "zy");

//        7、
        context.getBean("order");






//        System.out.println( "Hello World!" );
    }
}
