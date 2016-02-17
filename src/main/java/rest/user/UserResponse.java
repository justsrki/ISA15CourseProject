package rest.user;

import model.dao.User;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class UserResponse {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private int restaurantId;
    private int visits;
    private boolean activated;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.activated = user.getActivated();
        this.visits = user.getVisits();

        switch (user.getRole()) {
            case User.CUSTOMER:
                break;
            case User.MANAGER:
                this.restaurantId = user.getRestaurantId().getId();
                break;
            case User.ADMINISTRATOR:
                break;
            default:
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
