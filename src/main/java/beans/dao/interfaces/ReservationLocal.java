package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Reservation;
import model.dao.User;
import model.dto.reservation.ReservationDto;

import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface ReservationLocal extends AbstractLocal<Reservation> {

    Reservation create(ReservationDto reservationDto, User user);
    
}
