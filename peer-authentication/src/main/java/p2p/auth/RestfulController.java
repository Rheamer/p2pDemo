package p2p.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import p2p.auth.configs.WebSecurityConfig;
import p2p.auth.entities.User;
import p2p.auth.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins="http://localhost:8080")
public class RestfulController {
    private UserRepository userRepo;
    private WebSecurityConfig webSecConfig;

    @Autowired
    public RestfulController(
            UserRepository urep,
            WebSecurityConfig wsc){
        this.userRepo = urep;
        this.webSecConfig = wsc;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User processRegister(@RequestBody User user) {
        var passwordEncoder = webSecConfig.passwordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isPresent()){
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> listUsers(Model model) {
            var page = PageRequest.of(0, 12);
        return userRepo.findAll(page).getContent();
    }

}
