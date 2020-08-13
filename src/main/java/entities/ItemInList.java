package entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="ItemInList")
public class ItemInList {

    private String name;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private int amount;

    private boolean checked;

    @ManyToOne
    @JoinColumn(name="categoryListId", nullable=false)
    private CategoryList category;

    public ItemInList() {
        checked=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public CategoryList getCategory() {
        return category;
    }

    public void setCategory(CategoryList category) {
        this.category = category;
    }
}
