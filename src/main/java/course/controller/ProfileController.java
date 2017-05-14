package course.controller;

import course.dto.UserProfile;
import course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public @ResponseBody UserProfile ViewProfile(HttpSession httpSession, @PathVariable("id") long id) {
        return userService.getUserProfile(httpSession, id);
    }



}
