package course.controller;

import course.configuration.EsConfig;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.elasticsearch.domain.ElasticSite;
import course.service.SiteService;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;

import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by admin on 07.05.2017.
 */
@Controller
@RequestMapping("elasticsearch/")
public class ElasticController {

    @Autowired
    private EsConfig esConfig;

    @Autowired
    private ElasticSiteRepository elasticSiteRepository;

    @Autowired
    private SiteService siteService;

    @Value("${elasticsearch.index.name}")
    private String indexName;

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
