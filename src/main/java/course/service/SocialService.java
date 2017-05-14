package course.service;

import course.dao.UserRepository;
import course.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 14.05.2017.
 */
@Service
public class SocialService {

    private final SyncModelAndSessionService syncModelAndSessionService;

    @Autowired
    public SocialService(SyncModelAndSessionService syncModelAndSessionService) {
        this.syncModelAndSessionService = syncModelAndSessionService;
    }

    public String logging(Model model,
                          HttpSession httpSession,
                          ConnectionRepository connectionRepository,
                          UserRepository userRepository,
                          Twitter twitter,
                          Facebook facebook) {
        User user = new User();
        if ((connectionRepository.findConnections(Twitter.class).size() > 0)) {
            if (userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl()) == null) {
                user.setName(twitter.userOperations().getScreenName());
                user.setUserUrl(twitter.userOperations().getUserProfile().getProfileUrl());
                user.setRole("user");
                user.setUser_photo_url(twitter.userOperations().getUserProfile().getProfileImageUrl());
                userRepository.save(user);

                model.addAttribute("user", user.getUserUrl());
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
                syncModelAndSessionService.sync(StringUtils.EMPTY, model, httpSession);
            }
            else{
                user = userRepository.findByUserUrl(twitter.userOperations().getUserProfile().getProfileUrl());
                model.addAttribute("user",user.getUserUrl());
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("role", user.getRole());
                httpSession.setAttribute("id", user.getId());
                httpSession.setAttribute("img", twitter.userOperations().getUserProfile().getProfileImageUrl());
                syncModelAndSessionService.sync(StringUtils.EMPTY, model, httpSession);
            }
        }
        else {
            if ((connectionRepository.findConnections(Facebook.class).size() > 0)) {
                if ((userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink()))==null) {
                    user.setName(facebook.userOperations().getUserProfile().getName());
                    user.setUserUrl(facebook.userOperations().getUserProfile().getLink());
                    user.setRole("user");
                    user.setUser_photo_url("http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    userRepository.save(user);

                    model.addAttribute("user",user.getUserUrl());
                    httpSession.setAttribute("name", user.getName());
                    httpSession.setAttribute("role", user.getRole());
                    httpSession.setAttribute("id", user.getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    syncModelAndSessionService.sync(StringUtils.EMPTY, model, httpSession);
                }
                else{
                    user = userRepository.findByUserUrl(facebook.userOperations().getUserProfile().getLink());
                    model.addAttribute("user",user.getUserUrl());
                    httpSession.setAttribute("name", user.getName());
                    httpSession.setAttribute("role",user.getRole());
                    httpSession.setAttribute("id",user.getId());
                    httpSession.setAttribute("img", "http://graph.facebook.com/"+facebook.userOperations().getUserProfile().getId()+"/picture?type=square");
                    syncModelAndSessionService.sync(StringUtils.EMPTY, model, httpSession);
                }
            }
        }

        return "index";
    }

    public String disconnect(HttpSession httpSession, ConnectionRepository connectionRepository) {
        if(connectionRepository.findConnections(Twitter.class).size() > 0) {
            connectionRepository.removeConnection(connectionRepository.getPrimaryConnection(Twitter.class).getKey());
        } else {
            if (connectionRepository.findConnections(Facebook.class).size() > 0) {
                connectionRepository.removeConnection(connectionRepository.getPrimaryConnection(Facebook.class).getKey());
            }
        }
        httpSession.invalidate();
        return "index";
    }
}
