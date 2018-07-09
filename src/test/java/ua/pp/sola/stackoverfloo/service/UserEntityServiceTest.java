package ua.pp.sola.stackoverfloo.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.pp.sola.stackoverfloo.dao.UserDAO;
import ua.pp.sola.stackoverfloo.entities.UserEntity;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserEntityServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserWithCorrectData() {
        when(userDAO.createUser(any(UserEntity.class))).thenReturn(1);

        long result = userService.createUser("Admin", "admin");

        assertTrue(result == 1);
        verify(userDAO).createUser(any(UserEntity.class));
    }

    @Test
    public void testCreateUserWithMinUserName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("UserEntity name should be between 4 max 20 chars!");

        userService.createUser("Adm", "admin");
    }

    @Test
    public void testCreateUserWithMaxUserName() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("UserEntity name should be between 4 max 20 chars!");

        userService.createUser("Admgfsdgsdfgsfdgsdfgdsfgsfdgsdf", "admin");
    }

}
