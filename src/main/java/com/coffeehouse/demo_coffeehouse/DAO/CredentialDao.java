package com.coffeehouse.demo_coffeehouse.DAO;

import com.coffeehouse.demo_coffeehouse.model.Credential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class CredentialDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    private static final String GET_ALL_USER_CRED = """
            SELECT * FROM credentials
            """;

    public Optional<List<Credential>> getCredentialsByUsername(String username) {
        final String query = GET_ALL_USER_CRED + " WHERE username = :username";
        final Map<String, Object> param = Collections.singletonMap("username", username);
        try{
            return Optional.of(jdbcTemplate.query(query,param, (rs, rownum) -> new Credential(
                    rs.getString("username"), rs.getString("password"))));
        }catch (UsernameNotFoundException e){
            return Optional.empty();
        }
    }
}
