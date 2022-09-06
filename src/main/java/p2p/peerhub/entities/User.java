package p2p.peerhub.entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @JsonAnyGetter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    @Column(nullable = false, length = 60)
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Peer.class)
    @JoinColumn(name = "user_id")
    private Peer peer_data;

    public User() {
    }

}