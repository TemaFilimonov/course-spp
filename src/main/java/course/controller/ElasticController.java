package course.controller;

import course.configuration.EsConfig;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.elasticsearch.domain.ElasticSite;
import course.service.SiteService;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by admin on 07.05.2017.
 */
@Controller
@RequestMapping("elasticsearch/")
public class ElasticController {

    private final EsConfig esConfig;

    private final ElasticSiteRepository elasticSiteRepository;

    private final SiteService siteService;

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Autowired
    public ElasticController(EsConfig esConfig, ElasticSiteRepository elasticSiteRepository, SiteService siteService) {
        this.esConfig = esConfig;
        this.elasticSiteRepository = elasticSiteRepository;
        this.siteService = siteService;
    }

    @RequestMapping(value = "index/reset", method = RequestMethod.GET)
    public @ResponseBody CreateIndexResponse reset() throws Exception {
        esConfig.client()
                .admin()
                .indices()
                .prepareDelete(indexName)
                .execute()
                .actionGet();
        return esConfig.client()
                .admin()
                .indices()
                .prepareCreate(indexName)
                .setSource(XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("settings")
                    .field("number_of_shards", 1)
                    .field("number_of_replicas", 0)
                    .endObject()
                .endObject())
                .execute()
                .actionGet();
    }

    @RequestMapping(value = "reindex", method = RequestMethod.GET)
    public @ResponseBody String reindex() throws Exception {
        reset();
        List<Site> sites = siteService.getAll();
        sites
            .stream()
            .forEach(site -> elasticSiteRepository
                                        .save(new ElasticSite(site)));
        return "ok";
    }
}
