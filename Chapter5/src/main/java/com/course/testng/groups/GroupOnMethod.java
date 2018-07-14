package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMethod {

    @Test(groups = "server")
    public void test1(){
        System.out.println("这是服务端组的测试方法1111111");
    }


    @Test(groups = "server")
    public void test2(){
        System.out.println("这是服务端的测试方法2222222");

    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("这是客户端组的测试方法33333");
    }


    @Test(groups = "client")
    public void test4(){
        System.out.println("这是客户端组的测试方法444444");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer(){
        System.out.println("这是服务端运行之前运行的方法");
    }

    @AfterGroups("server")
    public void AfterGroupsOnServer(){
        System.out.println("这是服务端运行之后运行的方法");
    }


    @BeforeGroups("clinet")
    public void beforeGroupOnClient(){
        System.out.println("这是客户端运行之前运行的方法");
    }

    @AfterGroups("client")
    public void AfterGroupsOnClient(){
        System.out.println("这是客户端运行之后运行的方法");
    }
}
