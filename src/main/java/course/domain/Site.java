package course.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    public Site(String name, long ownerId, String createDate, String editDate, String tags){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.source = "{ \"A\": [ ] }";
        this.tags = tags;

    }
    public Site(String name, String tags){
        this.name = name;
        this.ownerId = 0;
        this.createDate = null;
        this.editDate = null;
        this.source = "{ \"A\": [ ] }";
        this.tags = tags;

    }
    public Site(String name, long ownerId, String createDate, String editDate, String source, String tags){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.source = source;
        this.tags = tags;

    }

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "edit_date")
    private String editDate;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "source")
    private  String source;

    @Column(name = "tags")
    private String tags;
}

