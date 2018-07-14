package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.shape.ShapeBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "测试1",httpMethod = "GET")   //接口文档
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest   装请求信息
        Cookie cookie = new Cookie("login","login");
        response.addCookie(cookie);
        //HttpServerletReponse   装响应信息
        return "恭喜获得cookies成功";
    }

    /**
     * 要求客户端携带cookies访问
     */
    @RequestMapping(value = "/getWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "测试2",httpMethod = "GET")   //接口文档
    public String getWithCookies(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            return "必须携带cookies信息";
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("login") && cookie.getValue().equals("login")){
                return "恭喜访问成功";
            }
        }
        return "必须携带cookies信息";

    }

    /**
     * 携带参数的get请求
     * 第一种url :key=value&key=value
     */
    @RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
    @ApiOperation(value = "测试3",httpMethod = "GET")    //接口文档
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",300);

        for (Map.Entry<String,Integer> entry : myList.entrySet()){
            System.out.println("Key="+entry.getKey()+ ";value=" +entry.getValue());
        }
        System.out.println(myList);
        return myList;
    }

    /**
     * 第二种携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */

    @RequestMapping(value = "/get/with/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "测试4",httpMethod = "GET")    //接口文档
    public Map myGetList(@PathVariable Integer start,@PathVariable Integer end){

        Map<String,Integer> myList = new HashMap<>();
        myList.put("鞋",400);
        myList.put("干脆面",1);
        myList.put("衬衫",500);

        System.out.println(myList);
        return myList;
    }
}
