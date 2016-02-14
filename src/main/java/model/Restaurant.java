package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
@XmlRootElement
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description", length = 2147483647)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_sum", nullable = false)
    private int ratingSum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_count", nullable = false)
    private int ratingCount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude", precision = 22)
    private Double latitude;
    @Column(name = "longitude", precision = 22)
    private Double longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Meal> mealCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Collection<FriendRating> friendRatingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<Reservation> reservationCollection;
    @OneToMany(mappedBy = "restaurantId")
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId")
    private Collection<model.Table> tableCollection;

    public Restaurant() {
    }

    public Restaurant(Integer id) {
        this.id = id;
    }

    public Restaurant(Integer id, String name, int ratingSum, int ratingCount) {
        this.id = id;
        this.name = name;
        this.ratingSum = ratingSum;
        this.ratingCount = ratingCount;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Collection<Meal> getMealCollection() {
        return mealCollection;
    }

    public void setMealCollection(Collection<Meal> mealCollection) {
        this.mealCollection = mealCollection;
    }

    @XmlTransient
    public Collection<FriendRating> getFriendRatingCollection() {
        return friendRatingCollection;
    }

    public void setFriendRatingCollection(Collection<FriendRating> friendRatingCollection) {
        this.friendRatingCollection = friendRatingCollection;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<model.Table> getTableCollection() {
        return tableCollection;
    }

    public void setTableCollection(Collection<model.Table> tableCollection) {
        this.tableCollection = tableCollection;
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
