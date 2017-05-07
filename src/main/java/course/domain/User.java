package course.domain;

import javax.persistence.*;

/**
 * Created by Артем Константинович on 02.10.2016.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public User(){
        this.name =null;
        this.userUrl=null;
    }
    public User(String name, String userUrl){
        this.name = name;
        this.userUrl = userUrl;
        this.role = "user";
    }
    @Column(name = "user_photo_url")
    private String user_photo_url;

    public String getUser_photo_url() {
        return user_photo_url;
    }

    public void setUser_photo_url(String user_photo_url) {
        this.user_photo_url = user_photo_url;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "user_url")
    private String userUrl;

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
