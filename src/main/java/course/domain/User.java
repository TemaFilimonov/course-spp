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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    public User(String name, String userUrl) {
        this.name = name;
        this.userUrl = userUrl;
        this.role = "user";
    }
    @Column(name = "user_photo_url")
    private String user_photo_url;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "user_url")
    private String userUrl;
}
