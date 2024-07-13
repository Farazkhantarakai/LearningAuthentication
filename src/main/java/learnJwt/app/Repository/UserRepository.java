package learnJwt.app.Repository;


import learnJwt.app.Modle.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);

}
