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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByActivated", query = "SELECT u FROM User u WHERE u.activated = :activated"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstName = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastName = :lastname"),
    @NamedQuery(name = "User.findByVisits", query = "SELECT u FROM User u WHERE u.visits = :visits")})
public class User implements Serializable {

    public static final String CUSTOMER = "customer";
    public static final String MANAGER = "manager";
    public static final String ADMINISTRATOR = "administrator";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "password", nullable = false, length = 25)
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "role", nullable = false, length = 25)
    private String role;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activated", nullable = false)
    private boolean activated;
    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visits", nullable = false)
    private int visits;
    @JoinTable(name = "friend", joinColumns = {
        @JoinColumn(name = "following", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "followed", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> userSet;
    @ManyToMany(mappedBy = "userSet", fetch = FetchType.LAZY)
    private Set<User> userSet1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Oauth2Account> oauth2AccountSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<FriendRating> friendRatingSet;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Log> logSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Invitation> invitationSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Reservation> reservationSet;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurantId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    private Set<Token> tokenSet;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String password, String role, boolean activated) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.activated = activated;
        this.visits = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<User> getUserSet1() {
        return userSet1;
    }

    public void setUserSet1(Set<User> userSet1) {
        this.userSet1 = userSet1;
    }

    public Set<Oauth2Account> getOauth2AccountSet() {
        return oauth2AccountSet;
    }

    public void setOauth2AccountSet(Set<Oauth2Account> oauth2AccountSet) {
        this.oauth2AccountSet = oauth2AccountSet;
    }

    public Set<FriendRating> getFriendRatingSet() {
        return friendRatingSet;
    }

    public void setFriendRatingSet(Set<FriendRating> friendRatingSet) {
        this.friendRatingSet = friendRatingSet;
    }

    public Set<Log> getLogSet() {
        return logSet;
    }

    public void setLogSet(Set<Log> logSet) {
        this.logSet = logSet;
    }

    public Set<Invitation> getInvitationSet() {
        return invitationSet;
    }

    public void setInvitationSet(Set<Invitation> invitationSet) {
        this.invitationSet = invitationSet;
    }

    public Set<Reservation> getReservationSet() {
        return reservationSet;
    }

    public void setReservationSet(Set<Reservation> reservationSet) {
        this.reservationSet = reservationSet;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Set<Token> getTokenSet() {
        return tokenSet;
    }

    public void setTokenSet(Set<Token> tokenSet) {
        this.tokenSet = tokenSet;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.dao.User[ id=" + id + " ]";
    }
    
}
