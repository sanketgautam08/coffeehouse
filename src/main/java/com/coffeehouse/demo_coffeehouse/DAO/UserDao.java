package com.coffeehouse.demo_coffeehouse.DAO;

import com.coffeehouse.demo_coffeehouse.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_USER = """
            INSERT INTO USERS (ID, LASTNAME, FIRSTNAME, USERNAME)
            VALUES (:userId, :lastName, :firstName, :userName)
            """;
    private static final String GET_ALL_USERS = """
            SELECT * FROM USERS
            """;

    public int addNewUser (UserInfo userInfo){
        jdbcTemplate.update(INSERT_USER, Map.of("userId", userInfo.userId(), "lastName", userInfo.lastName(), "firstName", userInfo.firstName(), "userName",userInfo.userName()));
        log.info("User Created with userId :: {}", userInfo.userId());
        return userInfo.userId();

    }

    public List<UserInfo> getAllUsers(){
       return jdbcTemplate.query(GET_ALL_USERS,(rs, rowNum) -> new UserInfo(rs.getInt("id"),rs.getString("lastname"),rs.getString("firstname"),rs.getString("username")));
    }

    public Optional<List<UserInfo>> getUser(int userId){
        final String query  = GET_ALL_USERS + "WHERE ID = :userId" ;
        final Map<String, Object> params = Map.of("userId", userId);

        try{
            return Optional.of(jdbcTemplate.query(query,params,(rs,rownum)-> new UserInfo(
                    rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("username"))));
        }catch(IncorrectResultSizeDataAccessException e){
            return Optional.empty();
        }
    }
}
