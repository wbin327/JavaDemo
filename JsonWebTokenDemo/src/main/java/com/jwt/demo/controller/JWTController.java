package com.jwt.demo.controller;

import com.jwt.demo.commons.JWTResult;
import com.jwt.demo.commons.JWTUtils;
import com.jwt.demo.commons.ResponseData;
import com.jwt.demo.commons.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class JWTController {

    @RequestMapping("/testJWT")
    @ResponseBody
    public Object testAll(HttpServletRequest request) throws Exception{
        String token = request.getHeader("Authorization");
        JWTResult result = JWTUtils.validateJWT(token);
        ResponseData responseData = new ResponseData();
        if(result.isSuccess()){
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());
            String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                    result.getClaims().getIssuer(),
                    result.getClaims().getSubject(),
                    1*60*1000);
            responseData.setToken(newToken);
            return responseData;
        }else{
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request) throws Exception{
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        ResponseData responseData = new ResponseData();
        if(name.equals("root") && password.equals("123456")){
            User user = new User("root", 1);
            String token = JWTUtils.createJWT(Integer.toString(user.getId()),
                    "wbin",
                    JWTUtils.generalSubject(user),
                    1*60*1000);
            responseData.setMsg("登录成功");
            responseData.setCode(200);
            responseData.setToken(token);
            responseData.setData(null);
        }else{
            responseData.setCode(500);
            responseData.setMsg("登录失败");
            responseData.setToken(null);
            responseData.setData(null);
        }
        return responseData;
    }
}
