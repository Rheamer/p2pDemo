package p2p.peerhub;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import p2p.peerhub.configs.WebSecurityConfig;
import p2p.peerhub.entities.Peer;
import p2p.peerhub.entities.User;
import p2p.peerhub.repositories.PeerRepository;
import p2p.peerhub.repositories.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins="http://p2pdemo:8080")
public class RestfulController {
    private UserRepository userRepo;
    private PeerRepository peerRepo;
    private WebSecurityConfig webSecConfig;

    @Autowired
    public RestfulController(
            UserRepository urep,
            PeerRepository prep,
            WebSecurityConfig wsc){
        this.userRepo = urep;
        this.peerRepo = prep;
        this.webSecConfig = wsc;
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User processRegister(User user) {
        var passwordEncoder = webSecConfig.passwordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepo.save(user);
        var peer = new Peer(user);
        peerRepo.save(peer);
        return savedUser;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isPresent()){
            return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> listUsers(Model model) {
        var page = PageRequest.of(0, 12);
        return userRepo.findAll(page).getContent();
    }

    @ResponseBody()
    @PostMapping("/configPeer/{userId}")
    public HttpStatus setPeerFields(@PathVariable("userId") Long userId, String ip, String port){
        var user = userRepo.findById(userId);
        if (user.isPresent()){
            Peer peer = user.get().getPeer_data();
            peer.setIp(ip);
            peer.setPort(port);
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}