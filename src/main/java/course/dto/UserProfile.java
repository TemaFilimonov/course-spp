package course.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private long id;
    private String name;
    private String photoUrl;
    private String socialUrl;
    private long sessionId;

    public UserProfile(long id, long sessionId, String name, String photoUrl, String socialUrl) {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
        this.photoUrl = photoUrl;
        this.socialUrl = socialUrl;
    }
}
