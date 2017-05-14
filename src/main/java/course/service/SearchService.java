package course.service;

import course.configuration.EsConfig;
import course.domain.Site;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by admin on 09.05.2017.
 */
@Service
public class SearchService {

    private final EsConfig esConfig;

    private final SiteService siteService;

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Autowired
    public SearchService(EsConfig esConfig, SiteService siteService) {
        this.esConfig = esConfig;
        this.siteService = siteService;
    }

    public
    List<Site> getSitesBySearchPhrase(String searchPhrase) throws Exception {
        SearchResponse response =  esConfig.client().prepareSearch(indexName)
                .setTypes("elasticsite")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders
                        .boolQuery()
                        .should(multiMatchQuery(searchPhrase,"source", "name")
                                .minimumShouldMatch("50%")))
                .setExplain(true)
                .execute()
                .actionGet();
        List<Site> result = new ArrayList<>();
        Arrays.asList(response.getHits().getHits())
                .stream()
                .forEach(hit -> result.add(siteService.getOneById(Long.parseLong(hit.getId()))));
        return result;
    }
}
