package course.service;

import course.dao.UserRepository;
import course.domain.User;
import course.dto.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by admin on 05.05.2017.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfile getUserProfile(HttpSession httpSession,  long id) {
        User user = userRepository.findById(id);
        return !httpSession.getAttributeNames().hasMoreElements()
                ? new UserProfile(id , (long)0, user.getName(),user.getUser_photo_url(),user.getUserUrl())
                : new UserProfile(id , (long)httpSession.getAttribute("id"), user.getName(),user.getUser_photo_url(),user.getUserUrl());
    }


    public List<User> getUserList() {
        return userRepository.findAllByOrderById();
    }
}
