package com.course.testng;

import org.testng.annotations.Test;

public class EXception {
    /**
     * 什么时候用到异常测试
     * 在期望结果为某一个异常的时候
     * 比如：传入不合法参数，成熟抛出异常
     */



    //测试结果是成功的异常测试

    @Test(expectedExceptions = RuntimeException.class)
    public void runRimeExceptionSuccess(){
        System.out.println("这是成功异常测试");
        throw new RuntimeException();
    }

    //测试结果失败的异常测试
    @Test(expectedExceptions = RuntimeException.class) //添加参数：expectedExceptions
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

}
