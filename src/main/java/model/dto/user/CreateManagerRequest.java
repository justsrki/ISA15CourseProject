package model.dto.user;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class CreateManagerRequest extends CreateUserRequest {
    private Integer restaurantId;

    public CreateManagerRequest() {
    }

    public CreateManagerRequest(String email, String firstName, String lastName, Integer restaurantId) {
        super(email, firstName, lastName);
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
