package com.course.testng;

import org.testng.annotations.Test;

import java.io.InterruptedIOException;

public class TimeoutTest {

    @Test(timeOut = 3000)   //单位为毫秒，超时注解参数：timeOut
    public void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000)
    public void testFailed() throws InterruptedException{
        Thread.sleep(4000);
    }
}
