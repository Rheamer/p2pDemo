package p2p.hub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import p2p.hub.configs.WebSecurityConfig;
import p2p.hub.entities.Peer;
import p2p.hub.entities.User;
import p2p.hub.repositories.PeerRepository;
import p2p.hub.repositories.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins="http://p2pdemo:8080")
public class RestfulController {
    private PeerRepository peerRepo;
    private WebSecurityConfig webSecConfig;

    @Autowired
    public RestfulController(
            PeerRepository prep,
            WebSecurityConfig wsc){
        this.peerRepo = prep;
        this.webSecConfig = wsc;
    }

    @ResponseBody()
    @PostMapping("/configPeer/{userId}")
    public HttpStatus setPeerFields(@PathVariable("peerId") Long peerId, String ip, String port){
        var peer = peerRepo.findById(peerId);
        if (peer.isPresent()){
            peer.get().setIp(ip);
            peer.get().setPort(port);
            peerRepo.save(peer.get());
            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}