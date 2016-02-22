package model.dao;

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
import javax.validation.constraints.Size;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "oauth2_account", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"social_network", "client_id"})})
@NamedQueries({
    @NamedQuery(name = "Oauth2Account.findAll", query = "SELECT o FROM Oauth2Account o"),
    @NamedQuery(name = "Oauth2Account.findById", query = "SELECT o FROM Oauth2Account o WHERE o.id = :id"),
    @NamedQuery(name = "Oauth2Account.findBySocialNetwork", query = "SELECT o FROM Oauth2Account o WHERE o.socialNetwork = :socialNetwork"),
    @NamedQuery(name = "Oauth2Account.findByClientId", query = "SELECT o FROM Oauth2Account o WHERE o.clientId = :clientId")})
public class Oauth2Account implements Serializable {

    public static final String GOOGLE_PLUS = "GP";
    public static final String FACEBOOK = "FB";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Column(name = "client_id", nullable = false, length = 50)
    private String clientId;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public Oauth2Account() {
    }

    public Oauth2Account(Integer id) {
        this.id = id;
    }

    public Oauth2Account(Integer id, String socialNetwork, String clientId) {
        this.id = id;
        this.socialNetwork = socialNetwork;
        this.clientId = clientId;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oauth2Account)) {
            return false;
        }
        Oauth2Account other = (Oauth2Account) object;
        return !((this.getId() == null && other.getId() != null) ||
                (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "model.dao.Oauth2Account[ id=" + id + " ]";
    }
    
}
