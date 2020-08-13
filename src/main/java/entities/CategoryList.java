package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name ="CategoryList")
public class CategoryList {

    @ManyToOne
    @JoinColumn(name="listId", nullable=false)
    private TripsList list;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long categoryListId;


    private String categoryListName;

    @OneToMany(mappedBy="category",cascade=CascadeType.REMOVE)
    private List<ItemInList> items;

    public CategoryList() {
    }

    public TripsList getList() {
        return list;
    }

    public void setList(TripsList list) {
        this.list = list;
    }

    public List<ItemInList> getItems() {
        return items;
    }

    public void setItems(List<ItemInList> items) {
        this.items = items;
    }

    public CategoryList(String categoryListName) {
        this.categoryListName = categoryListName;
    }

    public String getCategoryListName() {
        return categoryListName;
    }

    public void setCategoryListName(String categoryListName) {
        this.categoryListName = categoryListName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryList)) return false;
        CategoryList that = (CategoryList) o;
        return getCategoryListName().equals(that.getCategoryListName());
    }

    public void addItem(ItemInList item)
    {
        if(items==null)
            items=new ArrayList<>();
        items.add(item);
    }

    public Long getCategoryListId() {
        return categoryListId;
    }

    public void setCategoryListId(Long categoryListId) {
        this.categoryListId = categoryListId;
    }
}
