package p2p.peerhub.entities;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "peers")
public class Peer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user;

    @Column(nullable = false, unique = true, length = 50)
    private String ip;

    @Column(nullable = false, length = 20)
    private String port;

    public long getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
