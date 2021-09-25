package george.restfulllibraryapi.dtos.requests;

import george.restfulllibraryapi.models.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequest {

    private Long registerNumber;
    private String name;

    public Publisher build() {

        return new Publisher()
                .setRegisterNumber(this.registerNumber)
                .setName(this.name);
    }

}
