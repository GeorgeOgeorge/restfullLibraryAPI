package george.restfulllibraryapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "author")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 75)
    private String name;

    @Column(name = "social_network")
    private String socialNetwork;

    @Column(name = "personal_web_site")
    private String personalWebSite;

    @Column(name = "primary_language", nullable = false, length = 20)
    private String primaryLanguage;

    @Column(name = "picture_uri", unique = true)
    private String pictureURI;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

}