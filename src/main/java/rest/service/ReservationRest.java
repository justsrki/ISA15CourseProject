package rest.service;

import beans.dao.interfaces.ReservationLocal;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.TableLocal;
import model.dao.Restaurant;
import model.dao.Table;
import model.dao.User;
import model.dto.reservation.ReservationDto;
import model.dto.reservation.TablesStateRequest;
import model.dto.restaurant.TablesDto;
import rest.util.BasicResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    private RestaurantLocal restaurantBean;
    @EJB
    private ReservationLocal reservationBean;
    @EJB
    private TableLocal tableBean;

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
        if (reservationBean.create(reservationDto, user) == null) {
            throw ResponseExceptions.createBadRequest("Reservation cannot be created, try again.");
        }

        return BasicResponse.createCreated();
    }

}
