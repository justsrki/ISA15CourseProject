package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Reservation;

/**
 *
 * @author Srđan
 */
@Stateless
public class ReservationBean extends AbstractBean<Reservation> implements ReservationFacadeLocal {



    public ReservationBean() {
        super(Reservation.class);
    }
    
}
