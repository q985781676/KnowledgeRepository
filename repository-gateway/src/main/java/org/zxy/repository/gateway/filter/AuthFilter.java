package org.zxy.repository.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.zxy.repository.common.util.EncryptUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理所有请求的token
 * 进行统一解析后明文返回
 */
@Slf4j
public class AuthFilter extends ZuulFilter {

    /**
     * 设置统一开启过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤前缀
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public Object run() throws ZuulException {
        //获取网关的上下文，用于数据处理后统一转发
        RequestContext ctx = RequestContext.getCurrentContext();

        //从安全上下文中拿 到用户身份对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof OAuth2Authentication)){
            return null;
        }

        //转换为OAuth2Authentication来获取security中需要的认证信息
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        //获取认证信息
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        //取出用户身份信息
        String principal = userAuthentication.getName();
        //取出用户权限
        List<String> authorities = new ArrayList<>();
        //从userAuthentication取出权限，放在authorities
        userAuthentication.getAuthorities().forEach(c->authorities.add(((GrantedAuthority) c).getAuthority()));

        //其他的附加信息，统一传到微服务中处理
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        //转换格式
        Map<String,Object> jsonToken = new HashMap<>(requestParameters);
        jsonToken.put("principal",principal);
        jsonToken.put("authorities",authorities);

        //把身份信息和权限信息放在json中，加入http的header中,转发给微服务。明文进行base64转码传输
        ctx.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));

        return null;
    }
}
