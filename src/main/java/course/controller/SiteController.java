package course.controller;

import course.dto.Render;
import course.domain.Site;
import course.service.SiteService;
import course.service.utils.RenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/")
public class SiteController {

    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }


    @RequestMapping(value = "/site/info/{id}", method = RequestMethod.GET)
    public @ResponseBody
    List<Site> viewSites(@PathVariable("id") long id) {
       return siteService.getSitesByOwnerId(id);
    }

    @RequestMapping(value = "/site/source/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String viewSiteSource(@PathVariable("id") long id) {
       return siteService.getSiteSourceById(id);
    }

    @RequestMapping(value = "/delete/site/{id}", method = RequestMethod.GET)
    public String DeleteSite(HttpSession httpSession, @PathVariable("id") long id) {
        return siteService.delete(httpSession, id);
    }

    @RequestMapping(value = "/site/sortedByAlph/{count}", method = RequestMethod.GET)
    public @ResponseBody
    List<Site> viewMainSites(@PathVariable("count") int count) {
        return siteService.getSitesSortedByName(count);
    }

    @RequestMapping(value = "/site/sortedByCreate/{count}", method = RequestMethod.GET)
    public @ResponseBody
    List<Site> viewMainCreatedSites(@PathVariable("count") int count) {
       return siteService.getLastCreatedSites(count);
    }



    @RequestMapping(value = "/showsite/{id}", method = RequestMethod.GET)
    public  @ResponseBody
    Render renderSite(@PathVariable("id") long id) {
        return RenderUtils.build(siteService.getSiteSourceById(id));
    }
}
