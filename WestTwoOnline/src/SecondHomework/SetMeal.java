package SecondHomework;

public class SetMeal {
    private String setMealName;
    private double setMealPrice;
    private String chickenName;
    private Drinks drinks;

    public SetMeal() {
    }

    public SetMeal(String setMealName, double setMealPrice, String chickenName, Drinks drinks) {
        this.setMealName = setMealName;
        this.setMealPrice = setMealPrice;
        this.chickenName = chickenName;
        this.drinks = drinks;
    }

    public String getSetMealName() {
        return setMealName;
    }

    public void setSetMealName(String setMealName) {
        this.setMealName = setMealName;
    }

    public double getSetMealPrice() {
        return setMealPrice;
    }

    public void setSetMealPrice(double setMealPrice) {
        this.setMealPrice = setMealPrice;
    }

    public String getChickenName() {
        return chickenName;
    }

    public void setChickenName(String chickenName) {
        this.chickenName = chickenName;
    }

    public Drinks getDrinks() {
        return drinks;
    }

    public void setDrinks(Drinks drinks) {
        this.drinks = drinks;
    }

    @Override
    public String toString() {
        return "SetMeal{" +
                "setMealName='" + setMealName + '\'' +
                ", setMealPrice=" + setMealPrice +
                ", chickenName='" + chickenName + '\'' +
                ", drinks=" + drinks +
                '}'+"\n";
    }
}
