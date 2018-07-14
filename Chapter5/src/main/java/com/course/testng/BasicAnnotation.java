package com.course.testng;

//  alt + enter 补全缺失包

import org.testng.annotations.*;

public class BasicAnnotation {

    @Test //最基本的注解，用例把方法标记为测试的一部分
    public void testcase1(){
        System.out.println("这是测试用例1");
    }

    @Test
    public void testcase2(){
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod测试之前运行");
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod测试之后运行");
    }

    @BeforeClass
    public void BeforeClass(){
        System.out.println("BeforeClass这是类之前运行");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass这是类之后运行");
    }

    //类运行之前运行
    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("BeforeSuite执行套件");
    }

    //类运行之后运行
    @AfterSuite
    public void AfterSuite(){
        System.out.println("AfterSuite执行套件");
    }
}
