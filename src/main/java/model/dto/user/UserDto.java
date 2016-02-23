package model.dto.user;

import model.dao.User;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class UserDto {
    private int id;
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String role;
    private Integer restaurantId;
    private int visits;
    private boolean activated;
    private boolean following;

    public UserDto() {

    }

    public UserDto(User user, boolean following) {
        this(user);
        this.following = following;
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.activated = user.getActivated();
        this.visits = user.getVisits();
        this.restaurantId = user.getRestaurantId() != null ? user.getRestaurantId().getId() : null;
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
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

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
