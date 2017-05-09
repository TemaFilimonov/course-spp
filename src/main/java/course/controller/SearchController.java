package course.controller;

import course.configuration.EsConfig;
import course.domain.Site;
import course.elasticsearch.dao.ElasticSiteRepository;
import course.service.SearchService;
import course.service.SiteService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by admin on 09.05.2017.
 */
@Controller
@RequestMapping("search/")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @RequestMapping(value = "phrase={searchPhrase}", method = RequestMethod.GET)
    public @ResponseBody
    List<Site> testSearch(@PathVariable String searchPhrase) throws Exception {
       return searchService.getSitesBySearchPhrase(searchPhrase);
    }
}
