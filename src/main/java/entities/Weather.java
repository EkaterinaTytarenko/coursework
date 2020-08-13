package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="Weather")
public class Weather {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long weatherId;

    @OneToOne(mappedBy = "weather")
    private TripsList list;

    public boolean tempGroupOne=false;//20+
    public boolean tempGroupTwo=false;//+13-20
    public boolean tempGroupThree=false;//+5-13
    public boolean tempGroupFour=false;//from -5 to +5
    public boolean tempGroupFive=false;//to -5
    public boolean rainy=false;
    public boolean sunny=false;


    public void join(Weather w)
    {this.tempGroupOne|=w.tempGroupOne;
    this.tempGroupTwo|=w.tempGroupTwo;
    this.tempGroupThree|=w.tempGroupThree;
    this.tempGroupFour|=w.tempGroupFour;
    this.tempGroupFive|=w.tempGroupFive;
    this.rainy|=w.rainy;
    this.sunny|=w.sunny;
    }
}
