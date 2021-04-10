package comgrantcs.usermanagementapp.controller;

import comgrantcs.usermanagementapp.entity.User;
import comgrantcs.usermanagementapp.exception.ResourceNotFoundException;
import comgrantcs.usermanagementapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    @GetMapping
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    // update user
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") Long id) {
        User existing = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        return this.userRepository.save(existing);
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User existing = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        this.userRepository.delete(existing);
        return ResponseEntity.ok().build();
    }
}
