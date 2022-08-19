package p2p.peerhub.entities;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(nullable = false, length = 50)
    private String matching_password;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Peer.class)
    @JoinColumn(name = "user_id")
    private Peer peer_data;
}