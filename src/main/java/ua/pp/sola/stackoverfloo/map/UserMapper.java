package ua.pp.sola.stackoverfloo.map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.entities.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        int useId= resultSet.getInt("USER_ID");
        String userName = (resultSet.getString("USER_NAME"));
        String pass = (resultSet.getString("USER_PASSWORD"));
        UserRole role = (UserRole.valueOf(resultSet.getString("USER_ROLE")));
        boolean isActive = (resultSet.getBoolean("USER_ISACTIVE"));

        return new UserEntity(useId, userName, pass, role, isActive);
    }
}
