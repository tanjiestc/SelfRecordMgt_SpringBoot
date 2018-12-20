package com.tanjie.selfrecordmgt.config;

import com.tanjie.selfrecordmgt.filter.AuthenticationTokenFilter;
import com.tanjie.selfrecordmgt.service.impl.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.annotation.Resource;


/**
 * //@EnableWebSecurity 启用 Spring Security web 安全的功能
 *
 * @author tanjie
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 注册 401 处理器  没有携带token或者请求token无效，过期，错误
     */
    @Resource
    private UnauthorizedHandler unauthorizedHandler;

    /**
     * 注册 403 处理器  访问权限不足
     */
    @Resource
    private NoAccessHandler accessDeniedHandler;

    /**
     *
     */
    @Resource
    private UserDetailsServiceImpl userDetailsService;


    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean(){
        return new AuthenticationTokenFilter();
    }


    /**
     * 本次 json web token 权限控制的核心配置部分
     * 在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
     * 通过添加过滤器将 token 解析，将用户所有的权限写入本次会话
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/**").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html").permitAll()
                // 需携带有效 token
                .antMatchers("/auth").authenticated()
                // 需拥有 admin 这个权限
                .antMatchers("/admin").hasAuthority("admin")
                // 需拥有 ADMIN 这个身份
                .antMatchers("/ADMIN").hasRole("ADMIN")
                //除此之外的所有请求都必须经过验证
                .anyRequest().authenticated()
                .and()
                // 配置被拦截时的处理
                .exceptionHandling()
                // 添加 token 无效或者没有携带 token 时的处理
                .authenticationEntryPoint(this.unauthorizedHandler)
                //添加无权限时的处理
                .accessDeniedHandler(this.accessDeniedHandler)
                .and().csrf().disable()
                // 调整为让 Spring Security 不创建和使用 session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
