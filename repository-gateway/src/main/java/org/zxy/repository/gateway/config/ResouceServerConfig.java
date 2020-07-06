package org.zxy.repository.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 配置统一资源服务
 */
@Configuration
public class ResouceServerConfig {

  public static final String RESOURCE_ID = "res1";

  /** 认证中心资源配置 放行所有请求 */
  @Configuration
  @EnableResourceServer
  public class AuthServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/auth/**").permitAll();
    }
  }

  /** 前台服务资源 需要访问客户端拥有 ROLE_API 范围的权限，否则直接返回 */
  @Configuration
  @EnableResourceServer
  public class FrontServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/front/**").permitAll();
    }
  }
  /** 前台服务资源 需要访问客户端拥有 ROLE_API 范围的权限，否则直接返回 */
  @Configuration
  @EnableResourceServer
  public class BackServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
      resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/back/**").access("#oauth2.hasScope('ROLE_API')");
    }
  }

  // 配置其它的资源服务

}
