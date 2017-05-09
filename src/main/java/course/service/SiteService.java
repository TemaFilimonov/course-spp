package course.service;

import course.dao.FavoriteRepository;
import course.dao.SiteRepository;
import course.domain.Favorite;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.elasticsearch.domain.ElasticSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by admin on 05.05.2017.
 */
@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private ElasticSiteRepository elasticSiteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, FavoriteRepository favoriteRepository) {
        this.siteRepository = siteRepository;
        this.favoriteRepository = favoriteRepository;
    }

    public Site saveSite(HttpSession httpSession, Site site) {
        return siteRepository.save(new Site(
                site.getName(),
                (long) httpSession.getAttribute("id"),
                Calendar.getInstance().getTime().toString(),
                Calendar.getInstance().getTime().toString(),
                site.getTags()
        ));
    }

    public void saveExistSite(HttpSession httpSession, String source, long id) {
        Site site = siteRepository.findById(id);
        if (site.getOwnerId()==(long)httpSession.getAttribute("id")) {
            site.setSource(source);
            Date data = Calendar.getInstance().getTime();
            site.setEditDate(data.toString());
            Site savedSite = siteRepository.save(site);
            elasticSiteRepository.save(new ElasticSite(savedSite));
        }
    }

    public List<Site> getFavorites(HttpSession httpSession, long id) {
        List<Site> sites = new ArrayList<Site>();
        if (id==(long)httpSession.getAttribute("id")) {
            List<Favorite> favorite = favoriteRepository.findByUserId(id);
            favorite
                    .forEach(favoriteSite -> sites.add(siteRepository.findById(favoriteSite.getSiteId())));
        }
        Collections.reverse(sites);
        return sites;
    }

    public Site getOneById(Long id) {
        return siteRepository.findById(id);
    }

    public List<Site> getAll() {
        return siteRepository.findAll();
    }
}
