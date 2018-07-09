package ua.pp.sola.stackoverfloo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.sola.stackoverfloo.entities.TopicEntity;
import ua.pp.sola.stackoverfloo.service.TopicService;
import ua.pp.sola.stackoverfloo.template.QueryTemplates;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @Autowired
    TopicService topicService;

    @RequestMapping(value = "/user")
    public Principal user (Principal user ) {
    LOGGER.info("Print user" + user.toString());
        return user;
    }



//    @RequestMapping(value = "/login", method = {RequestMethod.GET})
//    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
//                               @RequestParam(value = "logout", required = false) String logout,
//                               Model model) {
//        LOGGER.info("Showing model:" + model);
////        model.addAttribute("error", error != null);
////        model.addAttribute("logout", logout != null);
//        return "login.html";
//    }
//
//    @RequestMapping(value = "/login", method = {RequestMethod.POST} )
//    public String postLogin(Model model) {
//        LOGGER.info("Showing model:" + model);
////        model.addAttribute("error", error != null);
////        model.addAttribute("logout", logout != null);
//        return "index.html";
//    }

//    @RequestMapping(value = "/login-auth", method = RequestMethod.POST)
//    public String postUser(@RequestParam(value = "error", required = false) String error,
//                               @RequestParam(value = "logout", required = false) String logout,
//                               Model model) {
//        LOGGER.info("Showing model:" + model);
////        model.addAttribute("error", error != null);
////        model.addAttribute("logout", logout != null);
//        return "login.html";
//    }

    @RequestMapping(value = "/denied")
    public String getAccsessDeniedPage() {

        return "denied.html";
    }

    @RequestMapping(value = "/error")
    public String getErrorPage() {

        return "error.html";
    }

}



