package SecondHomework;

import java.time.LocalDate;

public abstract class Drinks {
     protected String name;
     protected double cost; //成本
     protected LocalDate localDate; //生产日期
     protected int expirationDate; //保质期

    public Drinks() {
    }

    public Drinks(String name, double cost, LocalDate localDate, int expirationDate) {
        this.name = name;
        this.cost = cost;
        this.localDate = localDate;
        this.expirationDate = expirationDate;
    }
    public  abstract String toString();
//判断是否过期
    public boolean isOutOfDate(LocalDate localDate){
        //若调用此方法时的当前时间减去保质期小于drink对象创建时的生产日期，说明过期
        if(localDate.minusDays(expirationDate).isAfter(this.localDate)){
            return true;
        }
        else{
            return false;
        }


    }
}
