package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupOnClass1 {

    public void stu1(){
        System.out.println("GroupOnClass1中的stu1111111运行");
    }

    public void stu2(){
        System.out.println("GroupOnClass1中的stu122222运行");
    }
}
