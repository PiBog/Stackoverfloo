package ua.pp.sola.stackoverfloo.dao;

import ua.pp.sola.stackoverfloo.entities.UserEntity;

import java.util.List;

public interface UserDAO {

    UserEntity getUserByName(String userName);

    UserEntity getUserById(long id);

    int createUser(UserEntity userEntity);

    int updateUser(UserEntity userEntity);

    List<UserEntity> getUsersList();


}
