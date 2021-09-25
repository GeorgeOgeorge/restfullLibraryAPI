package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.Author;
import george.restfulllibraryapi.models.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private Long id;
    private String name;
    private String socialNetwork;
    private String personalWebSite;
    private String primaryLanguage;
    private String pictureURI;

    public Author build() {

        return new Author()
                .setId(this.id)
                .setName(this.name)
                .setSocialNetwork(this.socialNetwork)
                .setPersonalWebSite(this.personalWebSite)
                .setPrimaryLanguage(this.primaryLanguage)
                .setPictureURI(this.pictureURI);
    }
}
