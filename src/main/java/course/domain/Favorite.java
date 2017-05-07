package course.domain;

import javax.persistence.*;

/**
 * Created by Nox on 06.10.2016.
 */
@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public Favorite(){
        this.userId =0;
        this.siteId= 0;
    }
    public Favorite(long userId, long siteId){
        this.userId = userId;
        this.siteId = siteId;
    }

    @Column(name = "user_id")
    private long userId;

    @Column(name = "site_id")
    private long siteId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }
}
