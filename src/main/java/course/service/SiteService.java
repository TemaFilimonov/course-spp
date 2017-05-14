package course.service;

import course.dao.FavoriteRepository;
import course.dao.SiteRepository;
import course.domain.Favorite;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.elasticsearch.domain.ElasticSite;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by admin on 05.05.2017.
 */
@Service
public class SiteService {

    private final SiteRepository siteRepository;
    private final FavoriteRepository favoriteRepository;
    private final ElasticSiteRepository elasticSiteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, FavoriteRepository favoriteRepository, ElasticSiteRepository elasticSiteRepository) {
        this.siteRepository = siteRepository;
        this.favoriteRepository = favoriteRepository;
        this.elasticSiteRepository = elasticSiteRepository;
    }

    public Site saveSite(HttpSession httpSession, Site site) {
        Site savedSite = siteRepository.save(new Site(
                site.getName(),
                (long) httpSession.getAttribute("id"),
                Calendar.getInstance().getTime().toString(),
                Calendar.getInstance().getTime().toString(),
                site.getTags()
        ));
        elasticSiteRepository.save(new ElasticSite(savedSite));
        return savedSite;
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

    public List<Site> getLastCreatedSites(int count) {
        List<Site> page = new ArrayList<Site>();
        List<Site> sites = siteRepository.findAllByOrderByCreateDate();
        for(int i = 0;i<count;i++) {
            if (i >= sites.size()) {
                break;
            }
            else{
                page.add(sites.get(i));
            }
        }
        return page;
    }

    public List<Site> getSitesSortedByName(int count) {
        List<Site> sites;
        List<Site> page = new ArrayList<Site>();
        sites = siteRepository.findAllByOrderByName();
        for(int j = 0;j<count;j++) {
            if (j >= sites.size()) {
                break;
            }
            else{
                page.add(sites.get(j));
            }
        }
        return page;
    }

    public String delete(HttpSession httpSession, long id) {
        if ((long)httpSession.getAttribute("id") == siteRepository.findById(id).getOwnerId()) {
            elasticSiteRepository.deleteElasticSiteById(id);
            siteRepository.delete(id);
        }
        return "redirect:/profile?id="+httpSession.getAttribute("id").toString();
    }

    public String getSiteSourceById(long id) {
        return Optional.ofNullable(siteRepository.findById(id))
                .map(Site::getSource)
                .orElse(StringUtils.EMPTY);

    }

    public List<Site> getSitesByOwnerId( long id) {
        return siteRepository.findByOwnerId(id);
    }


}
