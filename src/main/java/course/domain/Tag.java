package course.domain;

import javax.persistence.*;

/**
 * Created by Nox on 05.10.2016.
 */
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public Tag(){
        this.tagValue =null;
    }
    public Tag(String tagValue){
        this.tagValue = tagValue;
    }

    @Column(name = "page_body")
    private String tagValue;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
