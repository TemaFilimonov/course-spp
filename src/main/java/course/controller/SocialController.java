package course.controller;

import course.dao.UserRepository;
import course.domain.User;
import course.service.SocialService;
import course.service.UserService;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class SocialController {

    private Twitter twitter;
    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private UserRepository userRepository;
    private SocialService socialService;
    private UserService userService;


    @Inject
    public SocialController(ConnectionRepository connectionRepository,
                            UserRepository userRepository,
                            Twitter twitter,
                            Facebook facebook,
                            SocialService socialService,
                            UserService userService) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
        this.twitter = twitter;
        this.facebook = facebook;
        this.socialService = socialService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String logging(Model model, HttpSession httpSession) {
        return socialService.logging(model,
                httpSession,
                connectionRepository,
                userRepository,
                twitter,
                facebook);
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    public String disconnect(HttpSession httpSession) {
        return socialService.disconnect(httpSession, connectionRepository);
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public @ResponseBody
    List<User> viewUserList() {
        return userService.getUserList();
    }
}
