package george.restfulllibraryapi.controllers;

import george.restfulllibraryapi.dtos.requests.UserRequest;
import george.restfulllibraryapi.dtos.responses.UserResponse;
import george.restfulllibraryapi.models.User;
import george.restfulllibraryapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "X-Total-Count")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> list(){
        return this.userService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> find(@PathVariable Long id) {
        Optional<User> newUser  = this.userService.find(id);
        if(newUser.isPresent())
            return ResponseEntity.ok().body(new UserResponse(newUser.get()));
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserResponse> insert(@RequestBody UserRequest userRequest) {
        User user = this.userService.insert(userRequest.build());
        return ResponseEntity.created(URI.create("user/" + user.getId())).body(new UserResponse(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> newUser = this.userService.find(id);
        if(newUser.isPresent() && newUser.get().getId().equals(user.getId()))
            return ResponseEntity.ok().body(new UserResponse(this.userService.update(user)));
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if(this.userService.find(id).isPresent()) {
            this.userService.delete(id);
            return ResponseEntity.ok(linkTo(UserController.class).withRel("All users"));
        }
        else
            return ResponseEntity.notFound().build();
    }
}
