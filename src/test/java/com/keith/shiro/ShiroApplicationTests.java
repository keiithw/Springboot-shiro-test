package com.keith.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroApplicationTests {


    private SimpleAccountRealm accountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager securityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        //向realm添加用户
        accountRealm.addAccount("keith","a323","admin");
        accountRealm.addAccount("brad","ccx6","user","visitor");
        securityManager.setRealm(accountRealm);
    }

    @Test
    public void testAuthentication() {
        //将SecurityManager交给环境
        SecurityUtils.setSecurityManager(securityManager);
        //获取当前主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("keith","a323");
        subject.login(token);
        System.out.println("用户名："+subject.getPrincipal());
        System.out.println("是否通过认证："+ subject.isAuthenticated());
        System.out.println("是否有admin角色："+subject.hasRole("admin"));
        System.out.println("是否有visitor角色："+subject.hasRole("visitor"));
    }

}
