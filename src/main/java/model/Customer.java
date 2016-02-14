package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
    @NamedQuery(name = "Customer.findBySurname", query = "SELECT c FROM Customer c WHERE c.surname = :surname"),
    @NamedQuery(name = "Customer.findByVisits", query = "SELECT c FROM Customer c WHERE c.visits = :visits")})
public class Customer implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;
    @Column(name = "visits")
    private Integer visits;
    @JoinTable(name = "friend", joinColumns = {
        @JoinColumn(name = "following_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "followed_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany
    private Collection<Customer> customerCollection;
    @ManyToMany(mappedBy = "customerCollection")
    private Collection<Customer> customerCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Oauth2Account> oauth2AccountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Collection<FriendRating> friendRatingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerUserId")
    private Collection<Invitation> invitationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerUserId")
    private Collection<Reservation> reservationCollection;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Collection<Customer> getCustomerCollection1() {
        return customerCollection1;
    }

    public void setCustomerCollection1(Collection<Customer> customerCollection1) {
        this.customerCollection1 = customerCollection1;
    }

    public Collection<Oauth2Account> getOauth2AccountCollection() {
        return oauth2AccountCollection;
    }

    public void setOauth2AccountCollection(Collection<Oauth2Account> oauth2AccountCollection) {
        this.oauth2AccountCollection = oauth2AccountCollection;
    }

    public Collection<FriendRating> getFriendRatingCollection() {
        return friendRatingCollection;
    }

    public void setFriendRatingCollection(Collection<FriendRating> friendRatingCollection) {
        this.friendRatingCollection = friendRatingCollection;
    }

    public Collection<Invitation> getInvitationCollection() {
        return invitationCollection;
    }

    public void setInvitationCollection(Collection<Invitation> invitationCollection) {
        this.invitationCollection = invitationCollection;
    }

    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Customer[ id=" + id + " ]";
    }
    
}
