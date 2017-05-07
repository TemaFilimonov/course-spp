package course.controller;

import course.dao.SiteRepository;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.elasticsearch.domain.ElasticSite;
import course.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by Nox on 09.10.2016.
 */
@Controller
@RequestMapping("/")
public class CreateController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ElasticSiteRepository elasticSiteRepository;

    @RequestMapping(value = "save/site", method = RequestMethod.POST)
    public String createSite(HttpSession httpSession, @RequestBody Site site) {
        return siteService.saveSite(httpSession, site);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String asfasfasf(HttpSession httpSession) {
        elasticSiteRepository.save(new ElasticSite("1",
                "Test",
                "asfasfas",
                "asfasfasf",
                "asfasfasf"));
        return "ok";
    }
}
