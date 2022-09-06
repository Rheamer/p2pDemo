package p2p.peerhub.entities;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "peers")
@Data
public class Peer {
    @JsonAnyGetter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user;

    @Column(nullable = false, unique = true, length = 50)
    private String ip;

    @Column(nullable = false, length = 20)
    @Getter
    private String port;

    public Peer(User user){
        this.user = user.getId();
    }
}
