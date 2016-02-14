package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "friend_rating")
@NamedQueries({
    @NamedQuery(name = "FriendRating.findAll", query = "SELECT f FROM FriendRating f"),
    @NamedQuery(name = "FriendRating.findByCustomerUserId", query = "SELECT f FROM FriendRating f WHERE f.friendRatingPK.customerUserId = :customerUserId"),
    @NamedQuery(name = "FriendRating.findByRestaurantId", query = "SELECT f FROM FriendRating f WHERE f.friendRatingPK.restaurantId = :restaurantId"),
    @NamedQuery(name = "FriendRating.findBySum", query = "SELECT f FROM FriendRating f WHERE f.sum = :sum"),
    @NamedQuery(name = "FriendRating.findByCount", query = "SELECT f FROM FriendRating f WHERE f.count = :count")})
@XmlRootElement
public class FriendRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FriendRatingPK friendRatingPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sum", nullable = false)
    private int sum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count", nullable = false)
    private int count;
    @JoinColumn(name = "customer_user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Restaurant restaurant;

    public FriendRating() {
    }

    public FriendRating(FriendRatingPK friendRatingPK) {
        this.friendRatingPK = friendRatingPK;
    }

    public FriendRating(FriendRatingPK friendRatingPK, int sum, int count) {
        this.friendRatingPK = friendRatingPK;
        this.sum = sum;
        this.count = count;
    }

    public FriendRating(int customerUserId, int restaurantId) {
        this.friendRatingPK = new FriendRatingPK(customerUserId, restaurantId);
    }

    public FriendRatingPK getFriendRatingPK() {
        return friendRatingPK;
    }

    public void setFriendRatingPK(FriendRatingPK friendRatingPK) {
        this.friendRatingPK = friendRatingPK;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (friendRatingPK != null ? friendRatingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRating)) {
            return false;
        }
        FriendRating other = (FriendRating) object;
        if ((this.friendRatingPK == null && other.friendRatingPK != null) || (this.friendRatingPK != null && !this.friendRatingPK.equals(other.friendRatingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FriendRating[ friendRatingPK=" + friendRatingPK + " ]";
    }
    
}
