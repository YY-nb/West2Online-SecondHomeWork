package SecondHomework;

import java.time.LocalDate;

public class Juice extends Drinks{
    public Juice() {
        super();
        expirationDate=2;
    }

    public Juice(String name, double cost, LocalDate localDate) {
        super(name, cost, localDate, 2);
    }

    @Override
    public String toString() {
        return "Juice{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", localDate=" + localDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
