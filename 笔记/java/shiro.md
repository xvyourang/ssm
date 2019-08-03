# shiro
## 1. shiro是什么
Apache Shiro 是 Java 的一个安全框架，用于用户登录及其授权管理。通过注解@RequiresPermissions可以具体控制到每一个动作的权限验证。

RDBC



**Subject**：主体，代表了当前 “用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是 Subject，如网络爬虫，机器人等；即一个抽象概念；所有 Subject 都绑定到 SecurityManager，与 Subject 的所有交互都会委托给 SecurityManager；可以把 Subject 认为是一个门面；SecurityManager 才是实际的执行者；

**SecurityManager**：安全管理器；即所有与安全有关的操作都会与 SecurityManager 交互；且它管理着所有 Subject；可以看出它是 Shiro 的核心，它负责与后边介绍的其他组件进行交互，如果学习过 SpringMVC，你可以把它看成 DispatcherServlet 前端控制器；

**Realm**：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。

## 2. shiro怎么用
### 2.1 shiro与spring集成使用

1.配置web.xml、shiro.xml、spring-mvc.xml。

2.在自定义Realm中定义用户授权和用户登录。

3.使用@RequiresPermission注解来进行访问权限验证。并配置异常处理。

### 2.2 shiro与spring集成

**在web.xml中加入shiro过滤器配置**

```
<!-- shiro过滤器定义 -->
    <filter>
        <filter-name>shiroFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
        <init-param>
            <!-- 该值缺省为false,表示生命周期由SpringApplicationContext管理,
            设置为true则表示由ServletContainer管理 -->
            <param-name>targetFilterLifecycle</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>shiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
<!-- shiro过滤器定义 end  -->
```
**spring-shiro.xml文件配置**

ps: anon 是不需要验证权限的的rul authc 是需要登录后路径

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:cache="http://www.springframework.org/schema/cache"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
	   http://www.springframework.org/schema/beans/spring-beans.xsd
	   http://www.springframework.org/schema/cache
	   http://www.springframework.org/schema/cache/spring-cache.xsd">

    <!--Shiro过滤器
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <!-- Shiro的核心安全接口,这个属性是必须的 -->
        <property name="securityManager" ref="securityManager"/>
        <!--权限验证url设置-->
        <property name="filterChainDefinitions">
        <value>
            /index.jsp = authc
            /unauthorized.jsp = 
            /login.jsp = anon 
            /logout = logout
            /** = user
        </value>
    	</property>
    </bean>
    <!-- 安全管理器 -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myRealm"/>
        <property name="sessionManager" ref="sessionManager"/>
        <property name="cacheManager" ref="shiroCacheManager"/>
    </bean>
    <bean id="myRealm" class="com.zhuoan.webapp.shiro.realm.MyRealm">
        <property name="credentialsMatcher" ref="hashedCredentialsMatcher"/>
    </bean>
    <!--加密算法 这个要与dao层往数据库写入密码的算法一致-->
    <bean id ="hashedCredentialsMatcher" class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
        <property name="hashAlgorithmName" value="MD5"/> <!-- 加密算法的名称 -->
        <property name="hashIterations" value="11"/> <!-- 配置加密的次数 -->
    </bean>
    <!--会话相关配置-->
    <!-- 会话管理器 -->
    <bean id="sessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
        <property name="globalSessionTimeout" value="300000"/>
        <property name="deleteInvalidSessions" value="true"/>
        <property name="sessionValidationSchedulerEnabled" value="true"/>
        <property name="sessionValidationScheduler" ref="sessionValidationScheduler"/>
        <property name="sessionDAO" ref="sessionDAO"/>
        <property name="sessionListeners" ref="sessionListener"/>
    </bean>
    <!--自定义会话监听-->
    <bean id="sessionListener" class="com.zhuoan.webapp.shiro.LoginListener"/>
    <!--会话DAO -->
    <bean id="sessionDAO" class="org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO">
    <!--活动会话缓存名，没有自定义就默认为shiro-activeSessionCache-->
    <property name="activeSessionsCacheName" value="shiro-activeSessionCache"/>
    <!--会话ID生成器 默认sessionIdGenerator-->
    <property name="sessionIdGenerator" ref="sessionIdGenerator"/>
    </bean>
    <!-- 会话ID生成器 -->
    <bean id="sessionIdGenerator" class="org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator"/>
    <!-- 会话验证调度器 -->
    <bean id="sessionValidationScheduler"
          class="org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler">
        <property name="interval" value="300000"/>
        <property name="sessionManager" ref="sessionManager"/>
    </bean>

    <!--缓存相关配置-->
    <bean id="ehCacheManager" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
        <property name="configLocation" value="classpath:ehcache.xml"/>
        <property name="shared" value="true"/>
    </bean>
    <bean id="ehcacheCacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager">
        <property name="cacheManager" ref="ehCacheManager"/>
    </bean>
    <!--shiro缓存-->
    <bean id="shiroCacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <property name="cacheManager" ref="ehCacheManager"/>
    </bean>
    <!-- 启用缓存注解开关 -->
    <cache:annotation-driven cache-manager="ehcacheCacheManager"/>
</beans>
```

**在spring-mvc.xml中加入shrio注解支持**

```
    <!--shiro注解支持-->
    <aop:config proxy-target-class="true"/>
    <bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager"/>
    </bean>
    <!--shiro注解支持end-->
```

   **自定义realm**

```
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserBiz userBiz;
    /**
     * 为当限前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();//获取用户名
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userBiz.getRoles(userName));//设置用户角色
        authorizationInfo.setStringPermissions(userBiz.getPermissions(userName));//设置权限
        return authorizationInfo;
    }
    /**
     * 验证当前登录的用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken loginToken = (UsernamePasswordToken) token;//获取网页登录信息
        String username = loginToken.getUsername();
        User user = userBiz.select(username);
        if (user != null) {
            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            authcInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
            return authcInfo;
        } else {
            return null;
        }
    }
}
```

**配置无权限页面**

**UnauthorizedException 无权限异常**

**UnauthenticatedException 未进行身份验证就执行权限操作时的异常。**

**DuplicateKeyException 数据库异常**

**以下是异常处理，可以为每个页面单独配置异常处理**

```
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
        //e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("failure");
        System.err.println("你没权限！！");
        return mv;
    }
```

**表结构设计，**

**基于用户-角色-权限，用户角色对应，角色权限对应。5张基础表设计**



### 2.3 盐加密

在自定义realm中加入<property name="credentialsMatcher">定义加密算法和加密次数。


    <property name="credentialsMatcher">
                <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                    <property name="hashAlgorithmName" value="MD5"/> <!-- 加密算法的名称 -->
                    <property name="hashIterations" value="11"/> <!-- 配置加密的次数 -->
                </bean>
            </property>
往数据库添加用户信息时要使用统一加密算法，这样才能正常登录。

### 2.4 用户顶号（单点登录）

添加会话监听，当有建立会话时往在线列表中加入这个会话，当会话过期或者关闭时移除会话。

用户登录时检查会话在线列表，如果存在则将上个会话踢出，加入此时的会话。

### 2.5 多个授权策略

在安全管理器中加入

```
<property name="authenticator" ref="authenticator"/>
```

并指定授权策略

```xml
<bean id="authenticator" class="org.apache.shiro.authc.pam.ModularRealmAuthenticator">
    <property name="authenticationStrategy" >
        <bean class="org.apache.shiro.authc.pam.AllSuccessfulStrategy"/>
    </property>
</bean>
```

|             策略             | 意义               |
| :--------------------------: | ------------------ |
|    AllSuccessfulStrategy     | 所有都满足的情况   |
| AtLeastOneSuccessfulStrategy | 至少一条满足的情况 |
|   FirstSuccessfulStrategy    | 第一条满足的情况   |

### 2.6 权限验证

在需要权限验证的方法/类/接口上使用@RequiresPermission注解来进行访问权限验证。

**权限注解**

```
@RequiresAuthentication
```

表示当前 Subject 已经通过 login 进行了身份验证；即 Subject.isAuthenticated() 返回 true。

```
@RequiresUser
```

表示当前 Subject 已经身份验证或者通过记住我登录的。

```
@RequiresGuest
```

表示当前 Subject 没有身份验证或通过记住我登录过，即是游客身份。

```
@RequiresRoles(value={“admin”, “user”}, logical= Logical.AND)
```

表示当前 Subject 需要角色 admin 和 user。

```
@RequiresPermissions (value={“user:a”, “user:b”}, logical= Logical.OR)
```

表示当前 Subject 需要权限 user：a 或 user：b。



## 3 注意点和出现的错误

### 3.1 配置会话管理器时注意使用的类

**DefaultSessionManager**：DefaultSecurityManager 使用的默认实现，用于 JavaSE 环境； **ServletContainerSessionManager**：DefaultWebSecurityManager 使用的默认实现，用于 Web 环境，其直接使用 Servlet 容器的会话； 

**DefaultWebSessionManager**：用于 Web 环境的实现，可以替代 ServletContainerSessionManager，自己维护着会话，直接废弃了 Servlet 容器的会话管理。

否则会抛出**UnauthenticatedException**异常



### 3.2 缓存冲突

	如果已经为框架配置了ehcache缓存，要系统和shiro分别配置缓存对象。

```
<!-- 使用ehcache缓存 -->
<bean id="ehCacheManager" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
    <property name="configLocation" value="classpath:ehcache.xml"/>
    <property name="shared" value="true"/>
</bean>
<bean id="ehcacheCacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager">
    <property name="cacheManager" ref="ehCacheManager"/>
</bean>
<!--shiro缓存-->
<bean id="shiroCacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
    <property name="cacheManager" ref="ehCacheManager"/>
</bean>
<!-- 启用缓存注解开关 -->
<cache:annotation-driven cache-manager="ehcacheCacheManager"/>
```























## 4 Springboot 集成shiro



## 4.1 pom 加入依赖

```xml
 <properties>
        <shiro.version>1.4.0</shiro.version>
 </properties>

<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>${shiro.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-web</artifactId>
    <version>${shiro.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-core</artifactId>
    <version>${shiro.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-ehcache</artifactId>
    <version>${shiro.version}</version>
</dependency>

```

## 4.2 配置

```java
package cn.xyr.ssm.common.config;

import cn.xyr.ssm.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro权限配置加载
 *
 */
@Configuration
public class ShiroConfig{

    //将自己的验证方式加入容器
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //LinkedHashMap是有序的
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
     /**
     * 哈希密码比较器。在myShiroRealm中作用参数使用
     * 登陆时会比较用户输入的密码，跟数据库密码配合盐值salt解密后是否一致。
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用md5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5( md5(""));
        return hashedCredentialsMatcher;
    }
}
```



ps：其他使用和上面的srpring一样





## 5. 在SpringMVC中开启Shiro注解授权的正确方法



临近年关，不知道是不是大家都空下来了，有时间学习了。最近好几个好学的童鞋在问我为什么他们在Srping的配置中文件中配置好了Shiro的注解支持Bean。但是在Controller中通过注解授权的时候就是不能生效。配置如下：





```html
<bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>
<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
      depends-on="lifecycleBeanPostProcessor"/>
<bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
    <property name="securityManager" ref="securityManager"/>
</bean>
```



首先我说，上面的配置是正确的，可是为什么不生效呢，今天我就来说说这事儿。

我们知道Shiro的注解授权是利有Spring的AOP实现的。在程序启动时会自动扫描作了注解的Class，当发现注解时，就自动注入授权代码实现。也就是说，要注入授权控制代码，第一处必须要让框架要可以扫描找被注解的Class 。而我们的Srping项目在ApplicationContext.xml中一般是不扫描Controller的，所以也就无法让写在Controller中的注解授权生效了。因此正确的作法是将这配置放到springmvc的配置文件中.这样Controller就可以通过注解授权了。

不过问题来了，通过上面的配置Controller是可以通过注解授权了，但是Services中依然不能通过注解授权。虽然说，如果我们在Controller控制了授权，那么对于内部调用的Service层就可以不再作授权，但也有例外的情况，比如Service除了给内部Controller层调用，还要供远程SOAP调用，那么就需要对Service进行授权控制了。同时要控制Controller和Service，那么采用相同的方式，我们可以在ApplicationContext.xml中配置类同的配置，以达到相同的效果。



```html
<bean id="serviceAdvisorAutoProxyCreator" class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
<bean id="serviceAuthorizationAttributeSourceAdvisor"
class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
	<property name="securityManager" ref="securityManager"/>
</bean>
```



在springmvc.xml中的配置改为



```html
<bean id="controllerAdvisorAutoProxyCreator"
class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
depends-on="lifecycleBeanPostProcessor"/>
<bean id="controllerAuthorizationAttributeSourceAdvisor"
class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
    <property name="securityManager" ref="securityManager"/>
</bean>
```



此时，我们在同一个项目中配置了两个，DefaultAdvisorAutoProxyCreator 和AuthorizationAttributeSourceAdvisor.

需要给它们指定不同的ID。

复制自 https://blog.csdn.net/hezhipin610039/article/details/50610547?locationNum=3&fps=1