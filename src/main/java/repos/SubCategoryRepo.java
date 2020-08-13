package repos;


import entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<Subcategory, Long> {
    Subcategory findBySubCategoryId(Long subcategoryId);
}
