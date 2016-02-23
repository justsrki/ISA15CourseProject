package beans.dao.beans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import beans.dao.interfaces.InvitationLocal;
import beans.dao.interfaces.ReservationLocal;
import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.TableLocal;
import model.dao.*;
import model.dto.reservation.ReservationDto;
import rest.util.ResponseExceptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class ReservationBean extends AbstractBean<Reservation> implements ReservationLocal {

    @EJB
    private RestaurantLocal restaurantBean;
    @EJB
    private TableLocal tableBean;
    @EJB
    private InvitationLocal invitationBean;


    public ReservationBean() {
        super(Reservation.class);
    }

    @Override
    public Reservation create(ReservationDto dto, User user) {
        Restaurant restaurant = restaurantBean.find(dto.getRestaurantId());
        if (restaurant == null) {
            return null;
        }

        Reservation reservation = new Reservation(null, dto.getStartDate(), dto.getEndDate());
        reservation.setUserId(user);
        reservation.setRestaurantId(restaurant);
        reservation.setTableSet(new HashSet<>());

        List<Table> tables = new ArrayList<>();
        for (Integer tableId: dto.getTables()) {
            Table table = tableBean.find(tableId);
            if (table == null) {
                return null;
            } else {
                tables.add(table);
            }
        }

        List<Table> reserved = tableBean.getReservedTables(dto.getStartDate(), dto.getEndDate(), restaurant);
        reserved.retainAll(tables);
        if (reserved.size() != 0) {
            throw ResponseExceptions.createBadRequest("Some of the selected tables is already reserved.");
        }

        for (Table table : tables) {
            table.getReservationSet().add(reservation);
            reservation.getTableSet().add(table);
        }

        Invitation invitation = new Invitation(null, true);
        invitation.setUserId(user);
        invitation.setReservationId(reservation);

        this.create(reservation);
        invitationBean.create(invitation);
        restaurantBean.edit(restaurant);

        return reservation;
    }
}
