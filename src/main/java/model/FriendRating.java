package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "friend_rating", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"restaurant_id", "customer_id"})})
@NamedQueries({
    @NamedQuery(name = "FriendRating.findAll", query = "SELECT f FROM FriendRating f"),
    @NamedQuery(name = "FriendRating.findById", query = "SELECT f FROM FriendRating f WHERE f.id = :id"),
    @NamedQuery(name = "FriendRating.findBySum", query = "SELECT f FROM FriendRating f WHERE f.sum = :sum"),
    @NamedQuery(name = "FriendRating.findByCount", query = "SELECT f FROM FriendRating f WHERE f.count = :count")})
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
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Customer customerId;
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

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRating)) {
            return false;
        }
        FriendRating other = (FriendRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FriendRating[ id=" + id + " ]";
    }
    
}
