package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "invitation")
@NamedQueries({
    @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i"),
    @NamedQuery(name = "Invitation.findById", query = "SELECT i FROM Invitation i WHERE i.id = :id"),
    @NamedQuery(name = "Invitation.findByAccepted", query = "SELECT i FROM Invitation i WHERE i.accepted = :accepted"),
    @NamedQuery(name = "Invitation.findByRating", query = "SELECT i FROM Invitation i WHERE i.rating = :rating")})
@XmlRootElement
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accepted", nullable = false)
    private short accepted;
    @Column(name = "rating")
    private Short rating;
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Reservation reservationId;
    @JoinColumn(name = "customer_user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerUserId;

    public Invitation() {
    }

    public Invitation(Integer id) {
        this.id = id;
    }

    public Invitation(Integer id, short accepted) {
        this.id = id;
        this.accepted = accepted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getAccepted() {
        return accepted;
    }

    public void setAccepted(short accepted) {
        this.accepted = accepted;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public Reservation getReservationId() {
        return reservationId;
    }

    public void setReservationId(Reservation reservationId) {
        this.reservationId = reservationId;
    }

    public Customer getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(Customer customerUserId) {
        this.customerUserId = customerUserId;
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
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Invitation[ id=" + id + " ]";
    }
    
}
