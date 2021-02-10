package com.wani.jobstudy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wani.jobstudy.config.jwt.JWTUtil;
import com.wani.jobstudy.config.jwt.JwtCheckFilter;
import com.wani.jobstudy.config.jwt.RefreshableJWTLoginFilter;
import com.wani.jobstudy.member.application.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;

    public SecurityConfig(MemberService memberService, ObjectMapper objectMapper, JWTUtil jwtUtil) {
        this.memberService = memberService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final RefreshableJWTLoginFilter loginFilter = new RefreshableJWTLoginFilter(authenticationManager(), memberService, jwtUtil, objectMapper);
        final JwtCheckFilter checkFilter = new JwtCheckFilter(authenticationManager(), memberService, jwtUtil);

        http.httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(loginFilter)
                .addFilter(checkFilter);

    }
}
