package model.dto.reservation;

import model.dao.Invitation;
import model.dao.Reservation;

import java.util.Date;

/**
 * @author - Srđan Milaković
 */
public class ReservationInvitationDto {
    private Integer reservationId;
    private Date startDate;
    private Date endDate;
    private String restaurantName;
    private Integer invitationId;
    private Short rating;

    public ReservationInvitationDto() {
    }

    public ReservationInvitationDto(Integer reservationId, Date startDate, Date endDate, String restaurantName,
                                    Integer invitationId, Short rating) {
        this.reservationId = reservationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.restaurantName = restaurantName;
        this.invitationId = invitationId;
        this.rating = rating;
    }

    public ReservationInvitationDto(Invitation invitation) {
        Reservation reservation = invitation.getReservationId();
        this.reservationId = reservation.getId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.restaurantName = reservation.getRestaurantId().getName();
        this.invitationId = invitation.getId();
        this.rating = invitation.getRating();
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }
}
