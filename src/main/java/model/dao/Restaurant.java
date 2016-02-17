package model.dao;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "restaurant")
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r"),
    @NamedQuery(name = "Restaurant.findById", query = "SELECT r FROM Restaurant r WHERE r.id = :id"),
    @NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name"),
    @NamedQuery(name = "Restaurant.findByRatingSum", query = "SELECT r FROM Restaurant r WHERE r.ratingSum = :ratingSum"),
    @NamedQuery(name = "Restaurant.findByRatingCount", query = "SELECT r FROM Restaurant r WHERE r.ratingCount = :ratingCount"),
    @NamedQuery(name = "Restaurant.findByLatitude", query = "SELECT r FROM Restaurant r WHERE r.latitude = :latitude"),
    @NamedQuery(name = "Restaurant.findByLongitude", query = "SELECT r FROM Restaurant r WHERE r.longitude = :longitude")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_sum", nullable = false)
    private int ratingSum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_count", nullable = false)
    private int ratingCount;
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<Meal> mealSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<FriendRating> friendRatingSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<Reservation> reservationSet;
    @OneToMany(mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<User> userSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<model.dao.Table> tableSet;

    public Restaurant() {
        this.ratingSum = 0;
        this.ratingCount = 0;
    }

    public Restaurant(Integer id) {
        this();
        this.id = id;
    }

    public Restaurant(Integer id, String name) {
        this(id);
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
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

    public Set<Meal> getMealSet() {
        return mealSet;
    }

    public void setMealSet(Set<Meal> mealSet) {
        this.mealSet = mealSet;
    }

    public Set<FriendRating> getFriendRatingSet() {
        return friendRatingSet;
    }

    public void setFriendRatingSet(Set<FriendRating> friendRatingSet) {
        this.friendRatingSet = friendRatingSet;
    }

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<model.dao.Table> getTableSet() {
        return tableSet;
    }

    public void setTableSet(Set<model.dao.Table> tableSet) {
        this.tableSet = tableSet;
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
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Restaurant[ id=" + id + " ]";
    }
    
}
