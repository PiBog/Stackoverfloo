package ua.pp.sola.stackoverfloo.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.pp.sola.stackoverfloo.dao.UserDAO;

import static org.junit.Assert.*;

public class UserAuthServiceTest {

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
    public void loadUserByUsername() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}