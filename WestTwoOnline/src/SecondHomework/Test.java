package SecondHomework;


import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

public class Test {  //测试类


    public static void main(String[] args) {
        West2FriedChickenRestaurant west2FriedChickenRestaurant=new West2FriedChickenRestaurant(100);
        West2FriedChickenRestaurant west2FriedChickenRestaurant1=new West2FriedChickenRestaurant(25);
        //先初始化几个饮料
        Beer beer1=new Beer("百威",10, LocalDate.now(),9);
        Beer beer2=new Beer("青岛",8,LocalDate.now(),8);
        Juice juice1=new Juice("橙汁",5,LocalDate.now());
        Beer beer3=new Beer("百威",10, LocalDate.now(),9);
        Juice juice2=new Juice("苹果汁",4,LocalDate.now());
        List<Drinks> drinksList=new LinkedList<>();
        drinksList.add(beer1);
        drinksList.add(beer2);
        drinksList.add(juice1);
        drinksList.add(beer3);
        drinksList.add(juice2);
        try {
            //测试进货
            west2FriedChickenRestaurant.stock(drinksList);
            //进货花了37元，输出余额
            System.out.println(west2FriedChickenRestaurant.getBalance());
            System.out.println(west2FriedChickenRestaurant.getBeerList());
            System.out.println(west2FriedChickenRestaurant.getJuiceList());

         //  测试进货异常
            west2FriedChickenRestaurant1.stock(drinksList);
            System.out.println(west2FriedChickenRestaurant.getBalance()); //余额会不足，会抛出异常
        }catch (OverdraftBalanceException e){
            e.printStackTrace();
        }
        finally {
            //测试售货

            west2FriedChickenRestaurant.sell(west2FriedChickenRestaurant.getMealList().get(0)); //售出套餐1
            System.out.println(west2FriedChickenRestaurant.getBalance());
            System.out.println(west2FriedChickenRestaurant.getBeerList()); //此时原本啤酒列表中的第一个啤酒已经移除
            west2FriedChickenRestaurant.sell(west2FriedChickenRestaurant.getMealList().get(3)); //售出套餐4
            System.out.println(west2FriedChickenRestaurant.getBalance());
            System.out.println(west2FriedChickenRestaurant.getJuiceList());
            west2FriedChickenRestaurant.sell(west2FriedChickenRestaurant.getMealList().get(3)); //售出套餐4，这时橙汁已售空，会抛异常

        }

    }
}
