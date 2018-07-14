package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesPost {

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
        List<Cookie> cookieList = store.getCookies();

        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies name=" +name+";cookies value="+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        String uri = bundle.getString("test.postcookies.uri");
        String testurl = this.url + uri;    //拼接测试地址

        //声明一个client对象，用来执行方法
        DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，post方法
        HttpPost post = new HttpPost(testurl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","zhangsan");
        param.put("age","18");

        //设置请求头信息
        post.setHeader("content-type","application.json");

        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //声明一个对象进行响应结果的存储
        String result;

        //设置cookies信息
        client.setCookieStore(this.store);

        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("postresult:"+result);

        //处理结果，判断是够符合预期
            //1.将返回结果转换为json对象
        JSONObject resultjson = new JSONObject(result);

        //获取结果值
        String success = (String) resultjson.get("message");    //强转
        String status = (String) resultjson.get("code");
        Assert.assertEquals("success",success);
        Assert.assertEquals("200",status);

    }
}

