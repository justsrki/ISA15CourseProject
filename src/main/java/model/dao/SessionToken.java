package model.dao;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "session_token")
@NamedQueries({
    @NamedQuery(name = "SessionToken.findAll", query = "SELECT s FROM SessionToken s"),
    @NamedQuery(name = "SessionToken.findById", query = "SELECT s FROM SessionToken s WHERE s.id = :id"),
    @NamedQuery(name = "SessionToken.findByValue", query = "SELECT s FROM SessionToken s WHERE s.value = :value"),
    @NamedQuery(name = "SessionToken.findByExpirationDate", query = "SELECT s FROM SessionToken s WHERE s.expirationDate = :expirationDate"),
    @NamedQuery(name = "SessionToken.findByType", query = "SELECT s FROM SessionToken s WHERE s.type = :type")})
public class SessionToken implements Serializable {
    public static final String API_ACCESS = "api_access";
    public static final String CONFIRM_REGISTRATION = "confirm_registration";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "value", nullable = false, length = 128)
    private String value;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public SessionToken() {
    }

    public SessionToken(Integer id) {
        this.id = id;
    }

    public SessionToken(Integer id, String value, String type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionToken)) {
            return false;
        }
        SessionToken other = (SessionToken) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SessionToken[ id=" + id + " ]";
    }
    
}
