package org.zxy.repository.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan("org.zxy.repository.back.mapper")
@EnableFeignClients(basePackages = {"org.zxy.repository.back"})
@EnableDiscoveryClient
@SpringBootApplication
public class BackApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackApplication.class, args);
  }
}
