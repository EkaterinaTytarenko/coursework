package repos;

import entities.ItemSubcategory;
import entities.Subcategory;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ItemSubcategoryRepo extends JpaRepository<ItemSubcategory,Long>{

    List<ItemSubcategory> findBySubcategory(Subcategory subCategory);
}
