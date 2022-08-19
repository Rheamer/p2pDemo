package p2p.peerhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import p2p.peerhub.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}