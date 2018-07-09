package ua.pp.sola.stackoverfloo.template;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.entities.UserRole;
import ua.pp.sola.stackoverfloo.map.UserMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTemplateTest {

    @InjectMocks
    private UserTemplate userDAO;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private UserEntity user;

    private static final String USER_NAME = "Vasya";
    private static final long USER_ID = 1L;

    @Before
    public void initialization() {
        user = new UserEntity(USER_ID, USER_NAME, "pass", UserRole.USER, true);
    }

    @Test
    public void testGetUsersList() {
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(user);
        String query = "SELECT * FROM STACKOVERFLOO.USERS";

        when(jdbcTemplate.query(eq(query), any(UserMapper.class))).thenReturn(usersList);

        List<UserEntity> resultList = userDAO.getUsersList();

        assertEquals(usersList.size(), resultList.size());
        assertEquals(USER_NAME, resultList.get(0).getUserName());
    }

    @Test
    public void testGetUserByName() {
        String query = "SELECT * FROM STACKOVERFLOO.USERS WHERE USER_NAME = ?";

        when(jdbcTemplate.queryForObject(eq(query), any(Object[].class), any(UserMapper.class))).thenReturn(user);

        UserEntity resultUser = userDAO.getUserByName(USER_NAME);

        assertUserEntity(resultUser);
    }

    @Test
    public void testGetUserById() {

        String query = "SELECT * FROM STACKOVERFLOO.USERS WHERE USER_ID = ?";

        when(jdbcTemplate.queryForObject(eq(query), any(Object[].class), any(UserMapper.class))).thenReturn(user);

        UserEntity resultUser = userDAO.getUserById(USER_ID);

        assertUserEntity(resultUser);
    }

    @Test
    public void testCreateUser() throws SQLException {
        when(jdbcTemplate.update(any(PreparedStatementCreator.class))).thenReturn(1);

        int result = userDAO.createUser(user);

        assertEquals(result, 1);
    }

    @Test
    public void testUpdateUser() {



        when(jdbcTemplate.update(any(PreparedStatementCreator.class))).thenReturn(1);

        int result = userDAO.createUser(user);

        assertEquals(result, 1);

    }

    private void assertUserEntity(UserEntity resultUser) {
        assertNotNull(resultUser);
        assertEquals(user.getUserId(), resultUser.getUserId());
        assertEquals(user.getUserName(), resultUser.getUserName());
        assertEquals(user.getRole(), resultUser.getRole());
        assertEquals(user.isActive(), resultUser.isActive());
    }
}