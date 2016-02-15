package bean2;

import java.util.List;
import javax.ejb.Local;
import model.Reservation;

/**
 *
 * @author Srđan
 */
@Local
public interface ReservationFacadeLocal {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    void remove(Reservation reservation);

    Reservation find(Object id);

    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);

    int count();
    
}
