package repos;

import entities.TripsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepo extends JpaRepository<TripsList, Long> {
        TripsList findByListId(Long listId);
}
