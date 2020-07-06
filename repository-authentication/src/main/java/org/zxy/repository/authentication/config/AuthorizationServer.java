package org.zxy.repository.authentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务端
 *
 * @author 98578
 * @create 2020-07-04 11:53
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  @Autowired private TokenStore tokenStore;
  @Autowired private ClientDetailsService clientDetailsService;
  @Autowired private AuthorizationCodeServices authorizationCodeServices;
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private JwtAccessTokenConverter accessTokenConverter;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired private JdbcClientDetailsService jdbcClientDetailsService;

  /**
   * @param security 用来配置令牌端点的安全约束
   * @throws Exception 异常
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
        .tokenKeyAccess("permitAll()") // oauth/token_key是公开
        .checkTokenAccess("permitAll()") // oauth/check_token公开
        .allowFormAuthenticationForClients() // 表单认证（申请令牌）
    ;
  }

  /**
   * 客户端详情信息在 这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
   *
   * @param client 配置客户端详情服务
   * @throws Exception 异常
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer client) throws Exception {
    client.withClientDetails(jdbcClientDetailsService);
    /*client
    .inMemory() // 使用in‐memory存储
    .withClient("c1") // client_id
    .secret(new BCryptPasswordEncoder().encode("secret")) // 客户端密钥
    .resourceIds("res1") // 可访问的资源列表
    .authorizedGrantTypes(
        "authorization_code",
        "password",
        "client_credentials",
        "implicit",
        "refresh_token") // 该client允许的授权类型
    // authorization_code,password,refresh_token,implicit,client_credentials
    .scopes("all") // 允许的授权范围
    .autoApprove(false) // 加上验证回调地址
    .redirectUris("http://www.baidu.com");*/
  }

  /**
   * @param endpoints 用来配置令牌（token）的访问端点和令牌服务(token services)。
   * @throws Exception 异常
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .authenticationManager(authenticationManager)
        .authorizationCodeServices(authorizationCodeServices)
        .tokenServices(tokenService())
        .allowedTokenEndpointRequestMethods(HttpMethod.POST);
  }

  /** 认证令牌服务 - 接入jwt */
  @Bean
  public AuthorizationServerTokenServices tokenService() {
    DefaultTokenServices service = new DefaultTokenServices();
    //
    service.setClientDetailsService(clientDetailsService);
    // 支持刷新令牌
    service.setSupportRefreshToken(true);
    // 存储策略
    service.setTokenStore(tokenStore);
    // 令牌增强
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
    service.setTokenEnhancer(tokenEnhancerChain);

    service.setAccessTokenValiditySeconds(72000); // 令牌默认有效期20小时
    service.setRefreshTokenValiditySeconds(720000); // 刷新令牌默认有效期
    return service;
  }

  /**
   * 设置授权码模式的授权码如何存取.
   *
   * @param dataSource 数据源
   */
  @Bean
  public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
    // 设置授权码模式的授权码如何存取
    return new JdbcAuthorizationCodeServices(dataSource);
  }

  /**
   * 将客户端信息存储到数据库
   * 大坑！
   * 注意：
   * 1.该地方数据库数据源注入服务
   * 2.JdbcClientDetailsService是ClientDetailsService实现类
   * 3.不可注入clientDetailsService 的bean，该bean已经被security包注入过
   *
   * @param dataSource 数据源
   */
  @Bean
  public JdbcClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
    JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
    clientDetailsService.setPasswordEncoder(passwordEncoder);
    return clientDetailsService;
  }
}
