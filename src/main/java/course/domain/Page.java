package course.domain;

import javax.persistence.*;

/**
 * Created by Nox on 05.10.2016.
 */
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public Page(){
        this.pageBody =null;
        this.siteId= 0;
    }
    public Page(String pageBody, long siteId){
        this.pageBody = pageBody;
        this.siteId = siteId;
    }

    @Column(name = "page_body")
    private String pageBody;

    @Column(name = "site_id")
    private long siteId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPageBody() {
        return pageBody;
    }

    public void setPageBody(String pageBody) {
        this.pageBody = pageBody;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }
}
