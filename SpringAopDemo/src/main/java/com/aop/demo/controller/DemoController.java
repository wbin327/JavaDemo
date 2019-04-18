package com.aop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value = "/before", method = RequestMethod.GET)
    public String before(){
        System.out.println("before advice demo");
        return "before advice";
    }


    @RequestMapping(value = "/after", method = RequestMethod.GET)
    public String after(){
        System.out.println("--------------- after advice ------------------");
        System.out.println("after advice demo");
        return "after advice";
    }

    @RequestMapping(value = "/around", method = RequestMethod.GET)
    public String around(){

        System.out.println("around advice demo");
        return "around advice";
    }
}
