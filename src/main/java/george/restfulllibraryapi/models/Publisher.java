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
@Table(name = "publisher")
@Entity
public class Publisher {

    @Id
    @Column(name = "register_number", nullable = false)
    private Long registerNumber;

    @Column(name = "name", nullable = false, unique = true, length = 30)
    private String name;

    @OneToMany(mappedBy = "publisher", orphanRemoval = true)
    private List<Book> books;

}