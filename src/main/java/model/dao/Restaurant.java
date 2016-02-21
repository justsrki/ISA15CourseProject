package model.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
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
    @NamedQuery(name = "Restaurant.findByLongitude", query = "SELECT r FROM Restaurant r WHERE r.longitude = :longitude"),
    @NamedQuery(name = "Restaurant.findByRows", query = "SELECT r FROM Restaurant r WHERE r.rows = :rows"),
    @NamedQuery(name = "Restaurant.findByColumns", query = "SELECT r FROM Restaurant r WHERE r.columns = :columns")})
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude", nullable = false)
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude", nullable = false)
    private double longitude;
    @NotNull
    @Column(name = "rows")
    private Short rows;
    @NotNull
    @Column(name = "columns")
    private Short columns;
    @Version
    @Column(name = "_version", columnDefinition = "integer DEFAULT 0", nullable = false)
    private int version;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<Meal> mealSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<FriendRating> friendRatingSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<Reservation> reservationSet;
    @OneToMany(mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Set<User> userSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private List<model.dao.Table> tableList;

    public Restaurant() {
        this.rows = 0;
        this.columns = 0;
    }

    public Restaurant(Integer id) {
        this.id = id;
        this.rows = 0;
        this.columns = 0;
    }

    public Restaurant(Integer id, String name, int ratingSum, int ratingCount, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.ratingSum = ratingSum;
        this.ratingCount = ratingCount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rows = 0;
        this.columns = 0;
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

    public Short getRows() {
        return rows;
    }

    public void setRows(Short rows) {
        this.rows = rows;
    }

    public Short getColumns() {
        return columns;
    }

    public void setColumns(Short columns) {
        this.columns = columns;
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

    public List<model.dao.Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<model.dao.Table> tableList) {
        this.tableList = tableList;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    public double getRating() {
        return getRatingCount() > 0 ? 1.0 * getRatingSum() / getRatingCount() : 0;
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
        return "model.dao.Restaurant[ id=" + id + " ]";
    }

}
