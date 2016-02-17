package rest.restaurant;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class RestaurantRequest {
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private String description;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;

    public RestaurantRequest() {
    }

    public RestaurantRequest(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
