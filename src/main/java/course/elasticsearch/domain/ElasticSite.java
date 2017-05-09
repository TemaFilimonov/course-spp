package course.elasticsearch.domain;

import course.domain.Site;
import course.service.utils.ElasticDataCleanUtil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by admin on 25.04.2017.
 */
@Document(indexName = "site", shards = 1, replicas = 0, refreshInterval = "-1")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ElasticSite{

    @Id
    private String id;
    private String name;
    private String source;

    private String ownerId;
    private String siteId;

    public ElasticSite(Site site) {
        this.id = String.valueOf(site.getId());
        this.name = site.getName();
        this.source = ElasticDataCleanUtil.clean(site.getSource());
        this.ownerId = String.valueOf(site.getOwnerId());
        this.siteId = String.valueOf(site.getId());
    }
}
