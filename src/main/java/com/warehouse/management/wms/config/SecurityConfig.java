package com.warehouse.management.wms.config;

import com.warehouse.management.wms.handler.AuthenticationFailureHandlerImpl;
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
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建security过滤器链
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        Customizer<FormLoginConfigurer<HttpSecurity>> customizer = configurer -> configurer.loginPage("/login")
                .successHandler(new AuthenticationSuccessHandlerImpl("/main", true))
                .failureHandler(new AuthenticationFailureHandlerImpl("/failure"));
        http.formLogin(customizer);


        Customizer<RememberMeConfigurer<HttpSecurity>> remember = rememberMeConfigurer -> rememberMeConfigurer.tokenRepository(persistentTokenRepository());//设置保存记住我数据的具体类型        http.rememberMe(remember);
        //授权配置
        http.authorizeRequests().requestMatchers("/login", "/failure").permitAll() //访问 /login地址时不做授权认证

                .anyRequest().authenticated();//访问其他地址时，必须认证成功后才可以访问
        //关闭csrf功能
        http.csrf(AbstractHttpConfigurer::disable);
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        repository.setCreateTableOnStartup(true);
        return repository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
