package com.drzewek.wfrp_npc_generator.configuration;

import com.drzewek.wfrp_npc_generator.model.Role;
import com.drzewek.wfrp_npc_generator.service.AuthorizationFilter;
import com.drzewek.wfrp_npc_generator.service.CustomAuthenticationProvider;
import com.drzewek.wfrp_npc_generator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Random;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class BeanConfig {

    private UserService userService;

    private AuthorizationFilter authorizationFilter;

    private static final String[] PUBLIC_ENDPOINTS = {"/user/register/**", "/user/verify/**", "/auth/**"};

    private static final String[] USER_ENDPOINTS = {"/npc/**", "/race/**"};
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //TODO change this so that only ADMIN users can access H2 console
        http.authorizeHttpRequests(auth -> auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll())
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests().requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers(USER_ENDPOINTS)
                .hasAnyAuthority(Role.USER.toString(), Role.CONFIRMED_USER.toString(), Role.ADMIN.toString())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(userService, passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
