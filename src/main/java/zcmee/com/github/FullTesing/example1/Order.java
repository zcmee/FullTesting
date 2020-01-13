package zcmee.com.github.FullTesing.example1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Meal> meals = new ArrayList<Meal>();

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal) {
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void cancel() {
        this.meals.clear();
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}
