package george.restfulllibraryapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "book")
@Entity
public class Book {

    @Id
    @Column(name = "ibns", nullable = false)
    private Long ibns;

    @Column(name = "name", nullable = false, length = 90)
    private String name;

    @Column(name = "page_number", nullable = false)
    private Short pageNumber;

    @Column(name = "cover_picture_uri")
    private String coverPictureURI;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_register_number")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Genre> genres;

    @Column(name = "start_read")
    private Date startRead = null;

    @Column(name = "finish_read")
    private Date finishRead = null;

}