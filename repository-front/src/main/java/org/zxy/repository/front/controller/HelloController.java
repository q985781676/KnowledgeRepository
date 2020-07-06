package org.zxy.repository.front.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxy.repository.common.exception.AjaxResponse;

/**
 * @author 98578
 * @create 2020-07-04 11:13
 */
@RequestMapping("/r")
@RestController
public class HelloController {

    @RequestMapping("/hello1")
    @PreAuthorize("hasAuthority('p1')")
    public AjaxResponse test1(){
        return AjaxResponse.success("front-hello1");
    }

    @RequestMapping("/hello2")
    @PreAuthorize("hasAnyAuthority('p5')")
    public AjaxResponse test2(){
        return AjaxResponse.success("front-hello2");
    }

    @RequestMapping("/hello3")
    public AjaxResponse test3(){
        return AjaxResponse.success("front-hello3");
    }

    @RequestMapping("/hello4")
    public AjaxResponse test4(){
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return AjaxResponse.success(principal);
    }


}
