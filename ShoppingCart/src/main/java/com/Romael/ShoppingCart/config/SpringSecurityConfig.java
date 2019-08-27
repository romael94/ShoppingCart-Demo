package com.Romael.ShoppingCart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    //private final String USER_QUERY = "SELECT username, password, active FROM user WHERE username=?";
    //private final String ROLES_QUERY = "SELECT u.username, r.role FROM user u join user_role ur on (u.userid=ur.userid) join role r on (ur.roleid=r.roleid) WHERE u.username=?";

    @Autowired
    public SpringSecurityConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
    }

    @Value(value = "${spring.admin.username}")
    private String adminEmail;

    @Value(value = "${spring.admin.password}")
    private String adminPassword;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        //Pages dont require login
        http.authorizeRequests().antMatchers("/","/login","/registration").permitAll();

        http.authorizeRequests().antMatchers("/products","/mycart").access("hasRole('ROLE_USER','ROLE_ADMIN')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //Config for login
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/products")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password");

               /* http.authorizeRequests()
                .antMatchers("/login", "/registration","/css/**","/images/**","/products").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/products")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Database authentication
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("SELECT username, password, active FROM \"user\" WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.role FROM \"user\" u join user_role ur on (u.userid=ur.userid) join role r on (ur.roleid=r.roleid) WHERE u.username=?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder());

        // In memory authentication
        auth.inMemoryAuthentication()
                .withUser(adminEmail).password(adminPassword).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
