package com.warehouse.management.wms.config;

import com.warehouse.management.wms.handler.AuthenticationFailureHandlerImpl;
import com.warehouse.management.wms.handler.AuthenticationLogoutHandlerImpl;
import com.warehouse.management.wms.handler.AuthenticationLogoutSuccessHandlerImpl;
import com.warehouse.management.wms.handler.AuthenticationSuccessHandlerImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private DataSource dataSource;
    @Resource
    private UserDetailsService detailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建security过滤器链
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //登陆页面配置
        Customizer<FormLoginConfigurer<HttpSecurity>> formLogin = formLoginConfigurer -> formLoginConfigurer.loginPage("/login")
                .successHandler(new AuthenticationSuccessHandlerImpl("/main", true))
                .failureHandler(new AuthenticationFailureHandlerImpl("/failure"));
        http.formLogin(formLogin);

        //记住我
        Customizer<RememberMeConfigurer<HttpSecurity>> remember = rememberMeConfigurer -> rememberMeConfigurer
                .tokenRepository(persistentTokenRepository())//设置保存记住我数据的具体类型
                .rememberMeParameter("remember-me")//请求参数中，记住我参数名，默认remember-me
                .rememberMeCookieName("gents") //将登陆依据保存到数据库中 客户端通过cookie记录数据库唯一信息
                .rememberMeCookieDomain("localhost") //默认不写
                .tokenValiditySeconds(1800) // cookie保存时长 秒 默认1800秒
                .userDetailsService(detailsService);//设置自定义userDetailsService接口实现对象
        http.rememberMe(remember);

        Customizer<LogoutConfigurer<HttpSecurity>> logout = logoutConfigurer -> logoutConfigurer.logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(new AuthenticationLogoutSuccessHandlerImpl())
                .addLogoutHandler(new AuthenticationLogoutHandlerImpl());
        http.logout(logout);

        //授权配置
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                .requestMatchers("/login", "/failure").permitAll()//访问其他地址时，必须认证成功后才可以访问
                .anyRequest().authenticated()//访问 /login地址时不做授权认证
        );


        //关闭csrf功能
        http.csrf(AbstractHttpConfigurer::disable);
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        // 初始化参数 仅第一次启动项目的时候设置为true 后续要设置为false
        repository.setCreateTableOnStartup(false);
        return repository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
