package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Invitation;
import model.dao.Reservation;
import model.dao.User;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface InvitationLocal extends AbstractLocal<Invitation> {

    void inviteUsers(Reservation reservation, List<User> users);

    void inviteUser(Reservation reservation, User user);

    void setRating(Invitation invitation, int value, User user);
}
