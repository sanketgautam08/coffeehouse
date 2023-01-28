package com.coffeehouse.demo_coffeehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BasicSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails user1 = User.builder()
//                .username("Sanket")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER", "ADMIN")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("Sijan")
//                .password(passwordEncoder().encode("pass"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2);

//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/users/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}
