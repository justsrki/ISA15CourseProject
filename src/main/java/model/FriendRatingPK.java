package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Srđan
 */
@Embeddable
public class FriendRatingPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "customer_user_id", nullable = false)
    private int customerUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    public FriendRatingPK() {
    }

    public FriendRatingPK(int customerUserId, int restaurantId) {
        this.customerUserId = customerUserId;
        this.restaurantId = restaurantId;
    }

    public int getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(int customerUserId) {
        this.customerUserId = customerUserId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) customerUserId;
        hash += (int) restaurantId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRatingPK)) {
            return false;
        }
        FriendRatingPK other = (FriendRatingPK) object;
        if (this.customerUserId != other.customerUserId) {
            return false;
        }
        if (this.restaurantId != other.restaurantId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FriendRatingPK[ customerUserId=" + customerUserId + ", restaurantId=" + restaurantId + " ]";
    }
    
}
