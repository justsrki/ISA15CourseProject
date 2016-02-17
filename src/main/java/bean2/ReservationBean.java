package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Reservation;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class ReservationBean extends AbstractBean<Reservation> implements ReservationLocal {

    public ReservationBean() {
        super(Reservation.class);
    }
    
}
