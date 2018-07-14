package com.course.server;


import com.course.bean.Bean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "全部的post请求")
@RequestMapping("/v1") //所有方法路径必须加/v1
public class MyPostMethod {
    private static Cookie cookie;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "登录接口，成功后获取cookies")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "username",required = true) String username,
                        @RequestParam(value = "password",required = true) String password){
        //required参数验证参数是否必填
            if (username.equals("zhangsan") && password.equals("123456")){
                cookie = new Cookie("login","login");
                response.addCookie(cookie);
                System.out.println("哈哈哈哈");
                return "登录成功";
            }
            return "登录失败";
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody Bean u){
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies){
            System.out.println("cookies:"+c.getName()+c.getValue()+u.getUsername()+u.getPassword());

            if (c.getName().equals("login") && c.getValue().equals("login") && u.getUsername().equals("zhangsan") && u.getPassword().equals("123456")){
                Bean bean = new Bean();
                bean.setName("lisi");
                bean.setAge("18");
                bean.setSex("man");
                return bean.toString();
            }
        }
        return "参数不合法";

    }
}
