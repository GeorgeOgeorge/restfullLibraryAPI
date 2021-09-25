package george.restfulllibraryapi.services;

import george.restfulllibraryapi.dtos.responses.UserResponse;
import george.restfulllibraryapi.models.User;
import george.restfulllibraryapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> list() {
        List<UserResponse> usersResponse = new ArrayList<>();
        for(User user : this.userRepository.findAll())
            usersResponse.add(new UserResponse(user));
        return usersResponse;
    }

    public Optional<User> find(Long id) {
        return this.userRepository.findById(id);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User update(User user) {
        return this.userRepository.saveAndFlush(user);
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

}
