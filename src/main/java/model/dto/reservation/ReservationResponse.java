package model.dto.reservation;

import model.dao.Invitation;
import model.dao.Reservation;

import java.util.Date;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class ReservationResponse {
    private Integer id;
    private String restaurantName;
    private Date startDate;
    private Date endDate;

    public ReservationResponse() {
    }

    public ReservationResponse(Integer id, String restaurantName, Date startDate, Date endDate) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReservationResponse(Reservation reservation) {
        this.id = reservation.getId();
        this.restaurantName = reservation.getRestaurantId().getName();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
