package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String url;
    private ResourceBundle bundle;
    //用例存储cookies信息
    private CookieStore store;

    //测试方法运行之前运行
    @BeforeTest
    public void beforeTest(){
        //读取properties文件内容
       bundle =  ResourceBundle.getBundle("application", Locale.CANADA);
       url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //调用get接口逻辑
        HttpGet get = new HttpGet(this.url + bundle.getString("getCookies.uri"));
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
         this.store = client.getCookieStore();
        List <Cookie> cookieList = store.getCookies();

        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies name=" +name+";cookies value="+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void withCookies() throws IOException {
        String uri  = bundle.getString("test.getcookies.uri");
        String testurl = this.url + uri;
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        //设置cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        //获取响应状态码
        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println("statuscode:"+statuscode);

        if(statuscode == 200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println("result:"+result);
        }

    }

}
