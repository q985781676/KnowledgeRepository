package org.zxy.repository.authentication.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("repository-front")
public interface FrontFeignService {

    @RequestMapping("/hello1")
    public String test1();

}
