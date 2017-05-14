package course.controller;

import course.dao.FavoriteRepository;
import course.domain.Favorite;
import course.domain.Site;
import course.service.SiteService;
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
public class FavoriteController {

    private final SiteService siteService;

    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteController(SiteService siteService, FavoriteRepository favoriteRepository) {
        this.siteService = siteService;
        this.favoriteRepository = favoriteRepository;
    }

    @RequestMapping(value = "/user/favorite/{id}", method = RequestMethod.GET)
    public @ResponseBody List<Site> ViewFavorite(HttpSession httpSession, @PathVariable("id") long id) {
        return siteService.getFavorites(httpSession, id);
    }

    @RequestMapping(value = "/favorite/add/{siteId}", method = RequestMethod.POST)
    public @ResponseBody void addToFavorite(HttpSession httpSession, @PathVariable("siteId") long id) {
        favoriteRepository.save(new Favorite(Long.valueOf(httpSession.getAttribute("id").toString()), id));
    }
}
