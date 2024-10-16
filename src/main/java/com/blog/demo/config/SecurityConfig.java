package com.blog.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationManager authMgr;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
            BCryptPasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService)
            throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);  // No need for `and()`
        return authenticationManagerBuilder.build(); 
    }

    
        
     
   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disabling CSRF for simplicity (only for development purposes, enable in production)
        http
            .csrf(csrf -> csrf.disable())

            // Allow public access to these endpoints without authentication
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/register", "/auth/login", "/auth/welcome").permitAll() // Allow register and login
                .requestMatchers("/articles/all", "/articles/getbyid/**").permitAll() // Public article endpoints
                .requestMatchers("/articles/addArticle").hasAuthority("ADMIN") // Only admin can add articles
                .requestMatchers("/auth/user/**").authenticated() // Require authentication for user actions
                .requestMatchers("/auth/admin/**").authenticated() // Require authentication for admin actions
                .anyRequest().authenticated() // All other requests require authentication
            )

            // Use HTTP Basic Authentication (this can be removed if not needed)
            .httpBasic(Customizer.withDefaults())

            // Form-based login configuration
            .formLogin(form -> form
                .loginPage("/auth/login") // Custom login page
                .permitAll() // Allow everyone to access the login page
            )

            // Logout configuration (optional)
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .permitAll());

        return http.build();
    }*/
     @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity (enable in production)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()  // Public access to /login
                 // All other requests require authentication
                 .requestMatchers("/auth/register").permitAll()
                 .requestMatchers(HttpMethod.GET, "/auth/all/**").hasAnyAuthority("ADMIN")
                 .anyRequest().authenticated()
            );
            
            http.addFilter(new JWTAuthenticationFilter(authManager(http, bCryptPasswordEncoder, userDetailsService)));
            http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        // Optionally, add your JWT filter here
        // http.addFilter(new JWTAuthenticationFilter(authenticationManager())); // Make sure to define this appropriately

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    // Password Encoding
    /*
     * @Bean
     * public PasswordEncoder passwordEncoder() {
     * return new BCryptPasswordEncoder();
     * }
     */

}
