package rest.service;

import beans.dao.interfaces.InvitationLocal;
import beans.dao.interfaces.TokenLocal;
import model.dao.Invitation;
import model.dao.User;
import model.dto.reservation.*;
import rest.util.BasicResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author - Srđan Milaković
 */
@Path("/invitation")
public class InvitationRest {

    @EJB
    private InvitationLocal invitationBean;
    @EJB
    private TokenLocal tokenBean;

    @GET
    @Path("/{id}/reservation")
    @RolesAllowed(User.CUSTOMER)
    public Object getReservation(@PathParam("id") Integer id) {
        Invitation invitation = invitationBean.find(id);
        if (invitation == null) {
            throw ResponseExceptions.createNotFound("Invitation not found.");
        }

        return new ReservationResponse(invitation.getReservationId());
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed(User.CUSTOMER)
    public Object confirmInvitation(@PathParam("id") Integer id, @QueryParam("value") Boolean value, @Context User user) {
        if (value == null) {
            value = true;
        }

        Invitation invitation = invitationBean.find(id);
        if (invitation == null) {
            throw ResponseExceptions.createNotFound("Invitation not found.");
        }

        if (invitation.getAccepted() != null || invitation.getReservationId().getStartDate().before(new Date())) {
            throw ResponseExceptions.createBadRequest("Invitation is not valid.");
        }

        if (!user.equals(invitation.getUserId())) {
            throw ResponseExceptions.createForbidden();
        }

        invitation.setAccepted(value);
        invitationBean.edit(invitation);

        return BasicResponse.createChanged();
    }

    @GET
    @Path("/visits")
    @RolesAllowed(User.CUSTOMER)
    public Object getAllReservations(@Context User user) {
        Set<Invitation> invitations = user.getInvitationSet();
        List<ReservationInvitationDto> past = new ArrayList<>();
        List<ReservationInvitationDto> future = new ArrayList<>();

        invitations.forEach(invitation -> {
            if (invitation.getAccepted() != null && invitation.getAccepted()) {
                if (invitation.getReservationId().getStartDate().before(new Date())) {
                    past.add(new ReservationInvitationDto(invitation));
                } else {
                    future.add(new ReservationInvitationDto(invitation));
                }
            }
        });

        return new VisitsDto(past, future);
    }

    @POST
    @Path("/{id}/rating")
    @RolesAllowed(User.CUSTOMER)
    public Object setMark(@PathParam("id") Integer id, @Context User user, RatingDto ratingDto) {
        Invitation invitation = invitationBean.find(id);
        if (invitation == null) {
            throw ResponseExceptions.createNotFound();
        }

        if (ratingDto.getValue() == null || ratingDto.getValue() == null) {
            throw ResponseExceptions.createBadRequest("Bad request.");
        }

        if (!invitation.getUserId().equals(user)) {
            throw ResponseExceptions.createForbidden();
        }

        invitationBean.setRating(invitation, ratingDto.getValue(), user);

        return BasicResponse.createChanged();
    }

}
