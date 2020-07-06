package org.zxy.repository.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("org.zxy.repository.front.mapper")
@EnableFeignClients(basePackages = {"org.zxy.repository.front"})
@EnableDiscoveryClient
@SpringBootApplication
public class FrontApplication {

  public static void main(String[] args) {
    SpringApplication.run(FrontApplication.class, args);
  }
}
