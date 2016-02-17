package rest.restaurant;

import model.dao.Restaurant;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class RestaurantResponse {
    private int id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private double rating;
    private double friendRating;
    private boolean manager;

    public RestaurantResponse(int id, String name, String description, double latitude, double longitude,
                              double rating, double friendRating, boolean manager) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.friendRating = friendRating;
    }

    public RestaurantResponse(Restaurant restaurant, double friendRating) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getLatitude(),
                restaurant.getLongitude(), restaurant.getRating(), friendRating, false);
    }

    public RestaurantResponse(Restaurant restaurant, boolean manager) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getLatitude(),
                restaurant.getLongitude(), restaurant.getRating(), 0, true);
    }

    public RestaurantResponse(Restaurant restaurant) {
        this(restaurant, 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getFriendRating() {
        return friendRating;
    }

    public void setFriendRating(double friendRating) {
        this.friendRating = friendRating;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
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
