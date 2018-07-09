package ua.pp.sola.stackoverfloo.template;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;
import ua.pp.sola.stackoverfloo.dao.UserDAO;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.map.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserTemplate implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserTemplate.class.getName());

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<UserEntity> getUsersList() {
        String query = "SELECT * FROM STACKOVERFLOO.USERS";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public UserEntity getUserByName(String userName) {
        String query = "SELECT * FROM STACKOVERFLOO.USERS WHERE USER_NAME = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{userName}, new UserMapper());
    }

    @Override
    public UserEntity getUserById(long id) {
        String query = "SELECT * FROM STACKOVERFLOO.USERS WHERE USER_ID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new UserMapper());
    }

    @Override
    public int createUser(final UserEntity userEntity) {
        final String query = "INSERT INTO STACKOVERFLOO.USERS (USER_NAME, USER_PASSWORD, USER_ROLE, USER_ISACTIVE) VALUES(?,?,?,?)";


        return jdbcTemplate.update(connection -> {

                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, userEntity.getUserName().toUpperCase());
                statement.setString(2, userEntity.getUserPas());
                statement.setString(3, userEntity.getRole().toString());
                statement.setBoolean(4, userEntity.isActive());
                return statement;
        });

    }
/**
 Update password
 */
    @Override
    public int updateUser(final UserEntity userEntity) {
        final String query = "UPDATE STACKOVERFLOO.USERS " +
                "SET USER_PASSWORD  = ?, user_role = ?, user_isactive = ?  " +
                "WHERE USER_ID = ?";


        return jdbcTemplate.update(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userEntity.getUserPas());
            statement.setString(2, userEntity.getRole().toString());
            statement.setBoolean(2, userEntity.isActive());
            statement.setLong(2, userEntity.getUserId());
            return statement;
        });
    }


}
