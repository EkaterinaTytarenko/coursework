package services;

import entities.Item;
import entities.ItemInList;
import entities.ItemSubcategory;
import entities.Subcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repos.ItemListRepo;
import repos.ItemRepo;
import repos.ItemSubcategoryRepo;
import repos.SubCategoryRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemSubcategoryRepo itemSubcategoryRepo;

    @Autowired
    private ItemListRepo itemInListRepo;

    @Autowired
    private SubCategoryRepo subCategoryRepo;


    public List<ItemInList> findItemsInSubcategory(Long subCategoryId){
        Subcategory subcategory=subCategoryRepo.findBySubCategoryId(subCategoryId);
        List<ItemSubcategory> isList=itemSubcategoryRepo.findBySubcategory(subcategory);
        List<ItemInList> result=new ArrayList<>();

        for(ItemSubcategory is:isList){
            Item itemFromDb=itemRepo.findByItemId(is.getItem().getItemId());
            ItemInList item=new ItemInList();
            item.setName(itemFromDb.getName());
            item.setAmount(is.getAmount());
            result.add(item);
        }
        return result;
    }

    public ItemInList saveItemInList(ItemInList item)
    {return itemInListRepo.saveAndFlush(item);
    }

    public void delInList(ItemInList item)
    {itemInListRepo.delete(item);
    }

    public ItemInList findItemById(Long itemId)
    {return itemInListRepo.findById(itemId).get();
    }

}
