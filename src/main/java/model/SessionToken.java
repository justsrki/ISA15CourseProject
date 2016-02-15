package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Srđan
 */
@Entity
@Table(name = "session_token", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"value"})})
@NamedQueries({
        @NamedQuery(name = "SessionToken.findAll", query = "SELECT s FROM SessionToken s"),
        @NamedQuery(name = "SessionToken.findById", query = "SELECT s FROM SessionToken s WHERE s.id = :id"),
        @NamedQuery(name = "SessionToken.findByValue", query = "SELECT s FROM SessionToken s WHERE s.value = :value"),
        @NamedQuery(name = "SessionToken.findByExpirationDate", query = "SELECT s FROM SessionToken s WHERE s.expirationDate = :expirationDate")})
public class SessionToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "value", nullable = false, length = 128)
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public SessionToken() {
    }

    public SessionToken(Integer id) {
        this.id = id;
    }

    public SessionToken(Integer id, String value, Date expirationDate) {
        this.id = id;
        this.value = value;
        this.expirationDate = expirationDate;
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
