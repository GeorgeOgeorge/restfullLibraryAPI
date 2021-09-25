package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponse extends RepresentationModel<GenreResponse> {

    private String name;

    public GenreResponse(Genre genre) {
        this.name = genre.getName();

        this.add(linkTo(GenreController.class).slash(genre.getId()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + genre.getId()).withRel("edit genre"));
        this.add(linkTo(GenreController.class).slash("/" + genre.getId()).withRel("delete genre"));
        this.add(linkTo(GenreController.class).withRel("All genres"));
    }

}
