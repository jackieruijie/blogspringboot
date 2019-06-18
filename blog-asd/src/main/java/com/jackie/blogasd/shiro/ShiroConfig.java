package com.jackie.blogasd.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: blog-asd
 * @ClassName: ShiroConfig
 * @description:  配置权限信息
 * @author: jackie
 * @create: 2019-06-18 14:38
 * @VERSION:v1.0
 **/

@Configuration
public class ShiroConfig {


    /**
     * 凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        //密码加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //机密册数
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义Realm
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        ShiroRealm realm=new ShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }


    /**
     * WEB安全管理器
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }


    /**
     * 设置过滤规则
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
//        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/");
        filterFactoryBean.setUnauthorizedUrl("/unauth");

        //注意此处使用的是LinkedHashMap，是有顺序的，shiro会按从上到下的顺序匹配验证，匹配了就不再继续验证
        //所以上面的url要苛刻，宽松的url要放在下面，尤其是"/**"要放到最下面，如果放前面的话其后的验证规则就没作用了。
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return filterFactoryBean;

    }
}
