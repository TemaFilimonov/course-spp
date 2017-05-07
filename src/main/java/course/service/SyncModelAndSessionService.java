package course.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 05.05.2017.
 */
@Service
public class SyncModelAndSessionService {

    public String sync(String path, Model model, HttpSession httpSession) {
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("name", httpSession.getAttribute("name"));
        model.addAttribute("id", httpSession.getAttribute("id"));
        model.addAttribute("img", httpSession.getAttribute("img"));
        return path;
    }
}
