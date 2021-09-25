package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    private String name;
    private Short age;
    private String profilePicURI;

    public User build() {
        return new User()
                .setId(this.id)
                .setName(this.name)
                .setAge(this.age)
                .setProfilePicURI(this.profilePicURI);

    }

}
