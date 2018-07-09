package ua.pp.sola.stackoverfloo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.service.UserService;
import ua.pp.sola.stackoverfloo.template.QueryTemplates;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserService userService;

    /**
     * Get all users
     */
    @RequestMapping(value = "/admin/users/", method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getUsersList() {
        ResponseEntity responseEntity;
        List<UserEntity> userEntityList = userService.getUsersList();
        if (userEntityList.isEmpty()) {
            logger.info("UserEntity list is empty");
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity<>(userEntityList, HttpStatus.OK);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/users/user/{id}", method = RequestMethod.GET)
        public UserEntity getUserByID(@PathVariable (value = "id") long userId) {
        return userService.getUserById(userId);
    }


//    @RequestMapping(value = "/users/{userName}", method = RequestMethod.GET)
//    public UserEntity getUserByName(@PathVariable String userName) {
//
//        return userService.getUserByName(userName.toUpperCase());
//    }

    @RequestMapping(value = "/users/create/", method = RequestMethod.POST)
    public long create(@RequestBody String name, @RequestBody String pass) {
        return userService.createUser(name, pass);
    }

    @RequestMapping(value = "/admin/users/update/{id}/", method = RequestMethod.PUT)
    public int update(@RequestBody UserEntity user) {
        return userService.updateUser(user); // не забути закодувати пароль
    }

//    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
//    public void delete(@PathVariable(value = "id") Integer userId) {
//        userService.deleteUser(userId);
//    }


//    @ResponseStatus(HttpStatus.CREATED)
//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public int createUser(@RequestBody UserEntity userEntity) {
//        return userService.createUser(userEntity.getUserName(), userEntity.getUserPas());
//    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute UserEntity userEntity) {
//
//        userService.createUser(userEntity.getUserName(),userEntity.getUserPas())
//
//        LongnotifyService.addInfoMessage("Login successful");
//        return "redirect:/";
//    }

}


