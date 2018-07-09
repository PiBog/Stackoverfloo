package ua.pp.sola.stackoverfloo.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.pp.sola.stackoverfloo.dao.UserDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserDAO userDAO;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetUsersList() {

        userService.getUsersList();
        verify(userDAO).getUsersList();

    }

    @Test
    public void testGetUserByName() {

        String userName = "LOL";

        userService.getUserByName(userName);
        verify(userDAO).getUserByName(userName);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNameWithNull() {
        userService.getUserByName(null);
    }

    @Test
    public void testGetUserByNameWithEmpty() {
        exception.expect(IllegalArgumentException.class);//перевыряэмо тип ексепшина
        exception.expectMessage("UserEntity name can not be empty!"); // перевыряэмо повыдомлення ексепшина
        userService.getUserByName("");
    }

    @Test
    public void getUserById() {
    }

    @Test
    public void testCreateUser() {


    }

    @Test
    public void updateUser() {
    }
}