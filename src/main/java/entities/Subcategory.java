package entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long subCategoryId;


    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name="categoryId", nullable=false)
    private Category category;


    @OneToMany(mappedBy = "subcategory")
    Set<ItemSubcategory> items;
}
