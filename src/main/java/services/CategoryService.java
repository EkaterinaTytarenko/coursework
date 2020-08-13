package services;

import entities.CategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.CategoryListRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryListRepo categoryRepo;

    public CategoryList saveCategory(CategoryList category) {
        return categoryRepo.saveAndFlush(category);
    }

    public void delCategory(CategoryList category) {
        categoryRepo.delete(category);
    }

    public CategoryList getById(Long id){
        return categoryRepo.findByCategoryListId(id);
    }
}
