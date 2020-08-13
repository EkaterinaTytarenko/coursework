package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long categoryId;


    private String categoryName;

    @OneToMany(mappedBy="category",cascade=CascadeType.REMOVE)
    private Set<Subcategory> subcategorySet;
}
