package course.elasticsearch.dao;

import course.elasticsearch.domain.ElasticSite;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by admin on 25.04.2017.
 */
public interface ElasticSiteRepository extends ElasticsearchRepository<ElasticSite, String> {

}
