package org.zxy.repository.authentication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zxy.repository.authentication.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 98578
 * @create 2020-07-03 15:50
 */
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名称查找用户信息
        org.zxy.repository.authentication.entity.User user = userMapper.selectOneByUsername(username);

        //如果根据用户名没有对应用户信息返回null，交由调用的Manager统一处理异常
        if (user==null){
            return null;
        }

        //权限列表
        List<String> authorities = userMapper.selectAuthorityByUserId(user.getUserId());
        log.info("loadUserByUsername authorities:"+authorities);
        //返回用户信息
        return User.withUsername(user.getUsername()).password(user.getPassword())
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", authorities)
                )).build();
    }
}
