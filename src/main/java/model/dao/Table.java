package model.dao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Srđan
 */
@Entity
@javax.persistence.Table(name = "table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"restaurant_id", "row", "column"})})
@NamedQueries({
        @NamedQuery(name = "Table.findAll", query = "SELECT t FROM Table t"),
        @NamedQuery(name = "Table.findById", query = "SELECT t FROM Table t WHERE t.id = :id"),
        @NamedQuery(name = "Table.findByLabel", query = "SELECT t FROM Table t WHERE t.label = :label"),
        @NamedQuery(name = "Table.findByRow", query = "SELECT t FROM Table t WHERE t.row = :row"),
        @NamedQuery(name = "Table.findByColumn", query = "SELECT t FROM Table t WHERE t.column = :column"),
        @NamedQuery(name = "Table.getReserved", query = "SELECT t FROM Table t " +
                "INNER JOIN t.reservationSet r " +
                "WHERE r.restaurantId = :restaurant " +
                "AND r.startDate < :endDate AND r.endDate > :startDate")})
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 5)
    @Column(name = "label", length = 5)
    private String label;
    @Basic(optional = false)
    @NotNull
    @Column(name = "row", nullable = false)
    private int row;
    @Basic(optional = false)
    @NotNull
    @Column(name = "column", nullable = false)
    private int column;
    @JoinTable(name = "resevated_table", joinColumns = {
            @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Reservation> reservationSet;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;

    public Table() {
    }

    public Table(Integer id) {
        this.id = id;
    }

    public Table(Integer id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public Table(Integer id, int row, int column, String label) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.label = label;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Table)) {
            return false;
        }

        Table other = (Table) object;
        return !((this.getId() == null && other.getId() != null) ||
                (this.getId() != null && !this.getId().equals(other.getId())));
    }

    @Override
    public String toString() {
        return "model.dao.Table[ id=" + id + " ]";
    }

}
