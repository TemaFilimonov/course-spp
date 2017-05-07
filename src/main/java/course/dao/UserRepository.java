package course.dao;


import course.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nox on 02.10.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserUrl(String userUrl);
    User findById(long id);
    List<User> findAllByOrderById ();
}
