package exercise.controller;

import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Кодировщик BCrypt
    // Используйте для хеширования пароля
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    // BEGIN
    @PostMapping("")
    public void createUser(@RequestBody User user) {
        User newUser = new User();
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        userRepository.save(newUser);
    }
    // END
}
