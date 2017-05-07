package course.controller;

import course.dao.FavoriteRepository;
import course.dao.SiteRepository;
import course.domain.Favorite;
import course.domain.Site;
import course.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Nox on 06.10.2016.
 */
@Controller
@RequestMapping("/")
public class FavoriteController {

    private final SiteService siteService;

    @Autowired
    public FavoriteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @RequestMapping(value = "/user/favorite/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Site> ViewFavorite(HttpSession httpSession, @PathVariable("id") long id) {
        return siteService.getFavorites(httpSession, id);
    }

}
