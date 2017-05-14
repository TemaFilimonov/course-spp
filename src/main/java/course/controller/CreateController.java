package course.controller;

import course.domain.Site;
import course.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class CreateController {

    private final SiteService siteService;

    @Autowired
    public CreateController(SiteService siteService) {
        this.siteService = siteService;
    }

    @RequestMapping(value = "save/site", method = RequestMethod.POST)
    public @ResponseBody Site createSite(HttpSession httpSession, @RequestBody Site site) {
        return siteService.saveSite(httpSession, site);
    }
}
