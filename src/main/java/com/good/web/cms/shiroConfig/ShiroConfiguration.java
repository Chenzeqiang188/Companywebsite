package com.good.web.cms.shiroConfig;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * shiro配置项
 */
@Configuration
public class ShiroConfiguration {

    /**
     * 目前还缺少自定义权限配置类
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
        //处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
        //指定加密方式方式，也可以在这里加入缓存，当用户超过五次登陆错误就锁定该用户禁止不断尝试登陆
     @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }


    @Bean(name = "ShiroDataBaseRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroDataBaseRealm shiroRealm() {
        ShiroDataBaseRealm realm = new ShiroDataBaseRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        return securityManager;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager  securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
        filterChainDefinitionManager.put("/show.do", "anon");
        filterChainDefinitionManager.put("/show*", "anon");
        filterChainDefinitionManager.put("/default.jsp",  "authc");
        filterChainDefinitionManager.put("/top.jsp",  "authc");
        filterChainDefinitionManager.put("/menu.jsp",  "authc");
        filterChainDefinitionManager.put("/main.jsp",  "authc");
        //拦截无登陆用户所有操作
        filterChainDefinitionManager.put("/cms/**", "authc");
        filterChainDefinitionManager.put("/login.do", "anon");//anon 可以理解为不拦截
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
        public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
            DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
            daap.setProxyTargetClass(true);
            return daap;
        }

        @Bean
        public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
            AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
            aasa.setSecurityManager(securityManager);
        return aasa;
    }

}
