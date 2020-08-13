package repos;

import entities.CategoryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryListRepo extends JpaRepository<CategoryList, Long> {
    CategoryList findByCategoryListId(Long id);
}
