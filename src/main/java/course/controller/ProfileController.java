package course.controller;

import course.dao.UserRepository;
import course.domain.User;
import course.domain.UserProfile;
import course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Nox on 05.10.2016.
 */
@Controller
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public @ResponseBody UserProfile ViewProfile(HttpSession httpSession, @PathVariable("id") long id){
        return userService.getUserProfile(httpSession, id);
    }



}
