package p2p.hub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import p2p.hub.entities.Peer;

public interface PeerRepository extends JpaRepository<Peer, Long> {
}
