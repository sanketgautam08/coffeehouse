package com.coffeehouse.demo_coffeehouse.service;
import com.coffeehouse.demo_coffeehouse.model.Credential;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JdbcUserService implements UserDetailsService {

    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final CredentialService credentialService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential userCred = credentialService.getCredentials(username);
        UserDetails userDetails =  User
                .withUsername(userCred.getUsername())
                .password(passwordEncoder.encode(userCred.getPassword()))
                .authorities(userCred.getUsername().equals("sanket@coffeehouse") ? "ADMIN" : "USER")
                .build();
        return userDetails;
    }
}
