package repos;

import entities.ItemInList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemListRepo extends JpaRepository<ItemInList, Long> {
}
