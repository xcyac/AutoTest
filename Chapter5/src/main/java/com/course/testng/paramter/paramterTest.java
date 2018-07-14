package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class paramterTest {

    @Test
    @Parameters({"name","age"})       //参数化注解
    public void paramTest1(String name,int age){
        System.out.println("name = "+ name + "；age = "+age);
    }
}
