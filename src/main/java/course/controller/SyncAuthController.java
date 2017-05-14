package course.controller;

import course.service.SyncModelAndSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 05.05.2017.
 */
@Controller
@RequestMapping("/")
public class SyncAuthController {

    private final SyncModelAndSessionService syncModelAndSessionService;

    @Autowired
    public SyncAuthController(SyncModelAndSessionService syncModelAndSessionService) {
        this.syncModelAndSessionService = syncModelAndSessionService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String ViewCreateRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("create", model, httpSession);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String ViewEditRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("edit", model, httpSession);
    }

    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public String ViewFavoriteRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("/favorite", model, httpSession);
    }

    @RequestMapping(value = "/site", method = RequestMethod.GET)
    public String siteRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("/site", model, httpSession);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String ViewProfileRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("/profile", model, httpSession);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String ViewSearchRole(Model model, HttpSession httpSession) {
        return syncModelAndSessionService.sync("/search", model, httpSession);
    }
}
