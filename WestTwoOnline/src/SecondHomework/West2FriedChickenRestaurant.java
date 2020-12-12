package SecondHomework;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.Set;

public class West2FriedChickenRestaurant implements FriedChickenRestaurant{
    private double balance;
    private  List <Beer> beerList=new LinkedList<>();
    private  List<Juice>juiceList=new LinkedList<>();
    private static final List<SetMeal>mealList=new LinkedList<>();
    //选用LinkedList是因为后续可能要做频繁的增删操作，LinkedList在随机增删上效率比ArrayList快
    //无参构造方法
    public West2FriedChickenRestaurant() {
    }
    //有参构造方法，传入balance

    public West2FriedChickenRestaurant(double balance) {
        this.balance = balance;
    }


    //使用静态代码块对套餐列表初始化
    static {

        final SetMeal meal1=new SetMeal("脆皮炸鸡加百威",22,"脆皮炸鸡",new Beer("百威",10,LocalDate.now(),9));
        final SetMeal meal2=new SetMeal("肯德鸡加百威",26.5,"肯德鸡",new Beer("百威",10,LocalDate.now(),9));
        final SetMeal meal3=new SetMeal("脆皮炸鸡加青岛",20,"脆皮炸鸡",new Beer("青岛",8,LocalDate.now(),8));
        final SetMeal meal4= new SetMeal("脆皮炸鸡加橙汁",16,"脆皮炸鸡",new Juice("橙汁",5,LocalDate.now()));
        final SetMeal meal5=new SetMeal("脆皮炸鸡加苹果汁",19,"脆皮炸鸡",new Juice("苹果汁",4,LocalDate.now())) ;
        final SetMeal meal6= new SetMeal("肯德鸡加橙汁",20.5,"肯德鸡",new Juice("橙汁",4,LocalDate.now()));

         mealList.add(meal1);
         mealList.add(meal2);
         mealList.add(meal3);
         mealList.add(meal4);
         mealList.add(meal5);
         mealList.add(meal6);

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

    public List<Juice> getJuiceList() {
        return juiceList;
    }

    public void setJuiceList(List<Juice> juiceList) {
        this.juiceList = juiceList;
    }

    public  List<SetMeal> getMealList() {
        return mealList;
    }

    private void use(Beer thisBeer) throws IngredientSortOutException{
        for(Beer beer:beerList){  //对于beers列表的每一个beer

            if(beer.name.equals(thisBeer.name)){  //遍历到的beer的名字与方法传入的beer名字相同
                if(beer.isOutOfDate(LocalDate.now())){  //先判断有无过期
                    System.out.println("该啤酒已过期");
                    beerList.remove(beer);
                    return;
                }
                else {

                    beerList.remove(beer);
                    return;
                }
            }
        }
        //列表里没找到则抛出异常
        throw new IngredientSortOutException(thisBeer.name+"已售空！");
    }
    private void use(Juice thisJuice) throws IngredientSortOutException{
        for(Juice juice:juiceList){

            if(juice.name.equals(thisJuice.name)){
                if(juice.isOutOfDate(LocalDate.now())){  //先判断有无过期
                    System.out.println("该果汁已过期");
                    juiceList.remove(juice );
                    return;
                }
                else {

                    juiceList.remove(juice );
                    return;
                }
            }
        }
        //列表里没找到则抛出异常
        throw new IngredientSortOutException(thisJuice.name+"已售空！");
    }
    @Override
    public void sell(SetMeal setMeal) {
        Drinks  drinkType=setMeal.getDrinks();
        if(drinkType instanceof Beer){
            try {
                use((Beer) drinkType);
                balance+=setMeal.getSetMealPrice();
            } catch (IngredientSortOutException e){
                e.printStackTrace();
            }
        }
        if(drinkType instanceof Juice){

            try {
                use((Juice)drinkType);
                balance+=setMeal.getSetMealPrice();
            } catch (IngredientSortOutException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void stock(List<Drinks> drinksList) throws OverdraftBalanceException{  //接受一个饮料列表，然后遍历将啤酒和果汁加入对应的列表
        int drinkPrice=0;
        //计算要进货的饮料的总价格
        for (Drinks drinks:drinksList) {
            drinkPrice += drinks.cost;
        }
            //进货费用超出拥有余额，则抛异常

        if (drinkPrice >= balance) {
            throw new OverdraftBalanceException("进货费用超出拥有余额,进货差"+(drinkPrice-balance)+" 元");
        }
        //将对应的饮料存入对应的列表中
            else {
                for (Drinks drinks:drinksList) {
                     if (drinks instanceof Beer) {

                         beerList.add((Beer) drinks);
                         balance -= drinks.cost;
                }
                     if (drinks instanceof Juice) {
                         juiceList.add((Juice) drinks);
                         balance -= drinks.cost;
                }
            }
        }
    }


}
