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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "oauth2_account")
@NamedQueries({
    @NamedQuery(name = "Oauth2Account.findAll", query = "SELECT o FROM Oauth2Account o"),
    @NamedQuery(name = "Oauth2Account.findById", query = "SELECT o FROM Oauth2Account o WHERE o.id = :id"),
    @NamedQuery(name = "Oauth2Account.findBySocialNetwork", query = "SELECT o FROM Oauth2Account o WHERE o.socialNetwork = :socialNetwork"),
    @NamedQuery(name = "Oauth2Account.findByUserId", query = "SELECT o FROM Oauth2Account o WHERE o.userId = :userId")})
@XmlRootElement
public class Oauth2Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "social_network", nullable = false, length = 2)
    private String socialNetwork;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerId;

    public Oauth2Account() {
    }

    public Oauth2Account(Integer id) {
        this.id = id;
    }

    public Oauth2Account(Integer id, String socialNetwork, String userId) {
        this.id = id;
        this.socialNetwork = socialNetwork;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof Oauth2Account)) {
            return false;
        }
        Oauth2Account other = (Oauth2Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Oauth2Account[ id=" + id + " ]";
    }
    
}
