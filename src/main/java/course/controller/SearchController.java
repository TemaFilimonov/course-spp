package course.controller;

import course.domain.Site;
import course.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 09.05.2017.
 */
@Controller
@RequestMapping("search/")
public class SearchController {

    private final SearchService searchService;

    @Value("${elasticsearch.index.name}")
    private String indexName;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "phrase={searchPhrase}", method = RequestMethod.GET)
    public @ResponseBody
    List<Site> testSearch(@PathVariable String searchPhrase) throws Exception {
       return searchService.getSitesBySearchPhrase(searchPhrase);
    }
}
