package bean2;

import model.dao.Reservation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface ReservationLocal {

    void create(Reservation reservation);

    void edit(Reservation reservation);

    void remove(Reservation reservation);

    Reservation find(Integer id);

    List<Reservation> findAll();

    List<Reservation> findRange(int[] range);

    int count();
    
}
