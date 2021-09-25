package george.restfulllibraryapi.dtos.responses;

import george.restfulllibraryapi.controllers.GenreController;
import george.restfulllibraryapi.models.BookList;
import george.restfulllibraryapi.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends RepresentationModel<UserResponse> {

    private String name;
    private Short age;
    private String profilePicURI;
    private List<BookListResponse> bookLists;

    public UserResponse(User user) {
        List<BookListResponse> newBookLists = new ArrayList<>();
        for(BookList bookList : user.getBookLists())
            newBookLists.add(new BookListResponse(bookList));

        this.name = user.getName();
        this.age = user.getAge();
        this.profilePicURI = user.getProfilePicURI();
        this.bookLists = newBookLists;

        this.add(linkTo(GenreController.class).slash(user.getId()).withSelfRel());
        this.add(linkTo(GenreController.class).slash("/" + user.getId()).withRel("edit user"));
        this.add(linkTo(GenreController.class).slash("/" + user.getId()).withRel("delete user"));
        this.add(linkTo(GenreController.class).withRel("All users"));
    }
}
