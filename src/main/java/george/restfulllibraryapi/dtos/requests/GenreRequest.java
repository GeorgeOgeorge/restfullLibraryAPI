package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreRequest {

    private String name;

    public Genre build() {
        return new Genre()
                .setName(this.name);
    }
}
