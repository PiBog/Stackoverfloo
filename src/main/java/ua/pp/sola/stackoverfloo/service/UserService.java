package ua.pp.sola.stackoverfloo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pp.sola.stackoverfloo.dao.UserDAO;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.entities.UserRole;

import java.util.List;

import static ua.pp.sola.stackoverfloo.entities.UserRole.USER;


@Service(value = "userService")
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private  final UserRole DEFAULT_ROLE = USER;
    private  final boolean ACTIVE_STATUS = true;
    private  final int MAX_USER_NAME_SIZE = 20;
    private  final int MIN_USER_NAME_SIZE = 4;

    @Autowired
    private UserDAO userDAO;

    public List<UserEntity> getUsersList() {
        return userDAO.getUsersList();
    }

    public UserEntity getUserByName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("UserEntity name can not be empty!");
        }

        return userDAO.getUserByName(userName);
    }

    public UserEntity getUserById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("UserEntity id can not be " + id + "!");
        }
        return userDAO.getUserById(id);

    }

    public long createUser(String userName, String pass) {
        if (userName.length() > MAX_USER_NAME_SIZE || userName.length() < MIN_USER_NAME_SIZE) {
            throw new IllegalArgumentException("UserEntity name should be between " + MIN_USER_NAME_SIZE +
                    " max " + MAX_USER_NAME_SIZE + " chars!");
        }

        UserEntity userEntity = new UserEntity(userName, pass, DEFAULT_ROLE, ACTIVE_STATUS);
        userDAO.createUser(userEntity);
        return userDAO.getUserByName(userName).getUserId();
    }

    public int updateUser(UserEntity userEntity) {

        return userDAO.updateUser(userEntity);
    }


}
