package SecondHomework;

import java.time.LocalDate;

public class Beer extends Drinks{
    private float alcoholContent; //酒精度数


    public Beer() {
        super();
        expirationDate=30;
        alcoholContent=0;
    }

    public Beer(String name, double cost, LocalDate localDate, float alcoholContent) {
        super(name, cost, localDate, 30);
        this.alcoholContent = alcoholContent;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "alcoholContent=" + alcoholContent +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", localDate=" + localDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
