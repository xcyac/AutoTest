package com.course.testng.groups;

import org.testng.annotations.Test;
import sun.java2d.loops.GeneralRenderer;

@Test(groups = "stu")
public class GroupOnClass2 {


    public void stu1(){
        System.out.println("GroupOnClass222中的stu1运行");
    }

    public void stu2(){
        System.out.println("GroupOnClass222中的stu2运行");
    }}
