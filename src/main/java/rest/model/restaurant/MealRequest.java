package rest.model.restaurant;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author - Srđan Milaković
 */
public class MealRequest {
    @NotBlank(message = "Name can't be blank.")
    private String name;
    private String description;
    @NotNull
    private Double price;

    public MealRequest() {
    }

    public MealRequest(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
}
