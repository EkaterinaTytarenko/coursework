package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="List")
public class TripsList {

    @OneToMany(mappedBy="list",cascade=CascadeType.REMOVE)
    private List<CategoryList> categories;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long listId;

    public List<CategoryList> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryList> categories) {
        this.categories = categories;
    }

    @ManyToOne
    @JoinColumn(name="id", nullable=true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String name;


    public Long getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "weatherId", referencedColumnName = "weatherId")
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

}
