package course.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "site_id")
    private long siteId;

    public Favorite(long userId, long siteId) {
        this.userId = userId;
        this.siteId = siteId;
    }
}
