package org.example.gradgateway1.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.aot.generate.ValueCodeGenerator.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain customSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/api/company/**","/api/review/**","/api/jobPost/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/company/**","/api/jobPost/**").hasAnyAuthority("EMPLOYER","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/company/**","/api/jobPost/**").hasAnyAuthority("EMPLOYER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/company/","/api/jobPost/**").hasAnyAuthority("EMPLOYER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/review/**").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/review/**").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/review/**").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/user/all").hasAuthority("ADMIN")
                        .requestMatchers("/api/user/delete").hasAuthority("ADMIN")
                        .requestMatchers("/api/userdata/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/userdata/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll())
        .formLogin(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults());



        return http.build();
    }

    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
