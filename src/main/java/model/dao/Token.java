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
@Table(name = "token")
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
    @NamedQuery(name = "Token.findById", query = "SELECT t FROM Token t WHERE t.id = :id"),
    @NamedQuery(name = "Token.findByValue", query = "SELECT t FROM Token t WHERE t.value = :value"),
    @NamedQuery(name = "Token.findByExpirationDate", query = "SELECT t FROM Token t WHERE t.expirationDate = :expirationDate"),
    @NamedQuery(name = "Token.findByType", query = "SELECT t FROM Token t WHERE t.type = :type")})
public class Token implements Serializable {

    public static final String ACCESS_TOKEN = "AT";
    public static final String CONFIRM_REGISTRATION = "CR";

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
    @Size(min = 1, max = 2)
    @Column(name = "type", nullable = false, length = 2)
    private String type;
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User userId;

    public Token() {
    }

    public Token(Integer id) {
        this.id = id;
    }

    public Token(Integer id, String value, String type) {
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
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        return !((this.getId() == null && other.getId() != null) ||
                (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "model.dao.Token[ id=" + id + " ]";
    }
    
}
