package course.elasticsearch.domain;

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
}
