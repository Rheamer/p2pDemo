package p2p.hub.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "peers")
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
public class Peer {
    @Id
    @JsonIgnore
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user;

    @Column(nullable = false, unique = true, length = 50)
    private String ip;

    @Column(nullable = false, length = 20)
    private String port;

    public Peer(User user){
        this.user = user.getId();
    }

}
