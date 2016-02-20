package rest.model.restaurant;

import model.dao.Meal;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class MealResponse {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int restaurantId;

    public MealResponse() {
    }

    public MealResponse(Integer id, String name, String description, double price, int restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public MealResponse(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.description = meal.getDescription();
        this.price = meal.getPrice();
        this.restaurantId = meal.getRestaurantId().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
