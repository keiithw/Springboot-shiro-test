package com.keith.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class JdbcShiroTest {

    @Test
    public void testJdbc(){
        //创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:jdbcrealm.ini");
        SecurityManager securityManager = factory.getInstance();

        //将securityManager设置到环境
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        Subject subject2 = SecurityUtils.getSubject();

        //数据库存在
        UsernamePasswordToken token = new UsernamePasswordToken("keith","456");
        //数据库不存在，会报错
        //UsernamePasswordToken token2 = new UsernamePasswordToken("keit","4568");

        subject.login(token);
        //subject2.login(token2);
        System.out.println("是否认证："+subject.isAuthenticated());
        //System.out.println(subject2.isAuthenticated());
        System.out.println("是否有下载视频权限："+subject.isPermitted("video:download"));
    }

}
