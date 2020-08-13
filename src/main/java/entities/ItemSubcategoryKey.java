package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class ItemSubcategoryKey implements Serializable {

    @Column(name = "itemId")
    Long itemId;

    @Column(name = "subCategoryId")
    Long subCategoryId;

}
