package org.zxy.repository.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.authentication.feign.FrontFeignService;

/**
 * @author 98578
 * @create 2020-07-04 11:13
 */
@RestController
public class HelloController {

    @Autowired
    FrontFeignService frontFeignService;

    @RequestMapping("/hello")
    public String test1(){
        return frontFeignService.test1();
    }
}
