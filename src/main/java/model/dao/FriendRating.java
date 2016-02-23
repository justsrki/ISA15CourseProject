package model.dao;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Srđan
 */
@Entity
@Table(name = "friend_rating", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "user_id"})})
@NamedQueries({
        @NamedQuery(name = "FriendRating.findAll", query = "SELECT f FROM FriendRating f"),
        @NamedQuery(name = "FriendRating.findById", query = "SELECT f FROM FriendRating f WHERE f.id = :id"),
        @NamedQuery(name = "FriendRating.findBySum", query = "SELECT f FROM FriendRating f WHERE f.sum = :sum"),
        @NamedQuery(name = "FriendRating.findByCount", query = "SELECT f FROM FriendRating f WHERE f.count = :count"),
        @NamedQuery(name = "FriendRating.findByUserRestaurant", query = "SELECT f FROM FriendRating f " +
                "WHERE f.userId = :user AND f.restaurantId = :restaurant")})
public class FriendRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sum", nullable = false)
    private int sum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count", nullable = false)
    private int count;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;

    public FriendRating() {
    }

    public FriendRating(Integer id) {
        this.id = id;
    }

    public FriendRating(Integer id, int sum, int count) {
        this.id = id;
        this.sum = sum;
        this.count = count;
    }

    public double getRating() {
        return ((double) getSum()) / getCount();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FriendRating)) {
            return false;
        }
        FriendRating other = (FriendRating) object;
        return !((this.getId() == null && other.getId() != null) ||
                (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "model.dao.FriendRating[ id=" + id + " ]";
    }

}
