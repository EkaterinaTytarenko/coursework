package entities;

import javax.persistence.*;

@Entity
public class ItemSubcategory {
        @EmbeddedId
        ItemSubcategoryKey id;

        @ManyToOne
        @MapsId("itemId")
        @JoinColumn(name = "itemId")
        Item item;

        @ManyToOne
        @MapsId("subCategoryId")
        @JoinColumn(name = "subCategoryId")
        Subcategory subcategory;

        int amount;

        public Item getItem() {
                return item;
        }

        public int getAmount() {
                return amount;
        }

        public void setAmount(int amount) {
                this.amount = amount;
        }

        public void setItem(Item item) {
                this.item = item;
        }
}
