package p2p.peerhub.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "peers")
@Data
public class Peer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user;

    @Column(nullable = false, unique = true, length = 50)
    private String ip;

    @Column(nullable = false, length = 20)
    private String port;
}
