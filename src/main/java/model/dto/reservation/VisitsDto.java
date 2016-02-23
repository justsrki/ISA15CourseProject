package model.dto.reservation;

import model.dao.Invitation;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
public class VisitsDto {
    List<ReservationInvitationDto> past;
    List<ReservationInvitationDto> future;

    public VisitsDto() {
    }

    public VisitsDto(List<ReservationInvitationDto> past, List<ReservationInvitationDto> future) {
        this.past = past;
        this.future = future;
    }

    public List<ReservationInvitationDto> getPast() {
        return past;
    }

    public void setPast(List<ReservationInvitationDto> past) {
        this.past = past;
    }

    public List<ReservationInvitationDto> getFuture() {
        return future;
    }

    public void setFuture(List<ReservationInvitationDto> future) {
        this.future = future;
    }
}
