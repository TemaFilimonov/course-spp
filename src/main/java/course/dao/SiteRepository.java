package course.dao;

import course.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Артем Константинович on 06.10.2016.
 */

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findById(long id);
    List<Site> findByOwnerId (long ownerId);
    List<Site> findAllByOrderByName ();
    List<Site> findAllByOrderByCreateDate();
}

