package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.Reservation;

/**
 *
 * @author Srđan
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {



    public ReservationFacade() {
        super(Reservation.class);
    }
    
}
