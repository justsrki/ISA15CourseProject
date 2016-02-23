package model.dto.restaurant;

import model.dao.Meal;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class MealDto {
    private Integer id;
    @NotBlank(message = "Name cannot be blank.")
    private String name;
    private String description;
    @NotNull
    private Double price;
    private int restaurantId;

    public MealDto() {
    }

    public MealDto(Integer id, String name, String description, Double price, int restaurantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public MealDto(Meal meal) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
