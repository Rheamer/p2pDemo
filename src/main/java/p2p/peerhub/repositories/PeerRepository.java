package p2p.peerhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import p2p.peerhub.entities.Peer;

public interface PeerRepository extends JpaRepository<Peer, Long> {
}
