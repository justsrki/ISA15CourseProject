package rest.service;

import beans.dao.interfaces.*;
import model.dao.*;
import model.dto.reservation.ReservationResponse;
import model.dto.reservation.InvitationsDto;
import model.dto.reservation.ReservationDto;
import model.dto.reservation.TablesStateRequest;
import model.dto.restaurant.TablesDto;
import rest.util.BasicResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Path("/reservation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationRest {

    @EJB
    private UserLocal userBean;
    @EJB
    private RestaurantLocal restaurantBean;
    @EJB
    private ReservationLocal reservationBean;
    @EJB
    private TableLocal tableBean;
    @EJB
    private InvitationLocal invitationBean;

    @POST
    @Path("/tables")
    @RolesAllowed(User.CUSTOMER)
    public Object getTablesState(TablesStateRequest tablesStateRequest) {
        Restaurant restaurant = restaurantBean.find(tablesStateRequest.getRestaurantId());
        if (restaurant == null) {
            throw ResponseExceptions.createNotFound();
        }

        List<Table> tables = restaurant.getTableList();
        HashSet<Table> reservedTables = new HashSet<>(tableBean.getReservedTables(tablesStateRequest.getStartDate(),
                tablesStateRequest.getEndDate(), restaurant));

        TablesDto response = new TablesDto(restaurant.getRows(), restaurant.getColumns());
        tables.forEach(table -> {
            String type = reservedTables.contains(table) ? TablesDto.RESERVED : TablesDto.TABLE;
            response.setTable(table.getId(), table.getRow(), table.getColumn(), table.getLabel(), type);
        });

        return response;
    }

    @POST
    @RolesAllowed(User.CUSTOMER)
    public Object createReservation(@Context User user, ReservationDto reservationDto) {
        Reservation reservation = reservationBean.create(reservationDto, user);
        if (reservation == null) {
            throw ResponseExceptions.createBadRequest("Reservation cannot be created, try again.");
        }

        return new ReservationResponse(reservation.getId(), reservation.getRestaurantId().getName(),
                reservation.getStartDate(), reservation.getEndDate());
    }

    @GET
    @Path("/{id}")
    @RolesAllowed(User.CUSTOMER)
    public Object getReservation(@PathParam("id") Integer id) {
        Reservation reservation = reservationBean.find(id);
        if (reservation == null) {
            return ResponseExceptions.createNotFound();
        }

        return new ReservationResponse(reservation);
    }

    @POST
    @Path("/{id}")
    @RolesAllowed(User.CUSTOMER)
    public Object createInvitations(@PathParam("id") Integer id, @Context User user, InvitationsDto invitationsDto) {
        Reservation reservation = reservationBean.find(id);
        if (reservation == null) {
            return ResponseExceptions.createNotFound();
        }
        List<User> users = new ArrayList<>();
        invitationsDto.getUserIds().forEach(userId -> {
            User u = userBean.find(userId);
            if (user.getFollowingSet().contains(u)) {
                users.add(u);
            }
        });

        invitationBean.inviteUsers(reservation, users);

        return BasicResponse.createOk("Users invited.");
    }

}
