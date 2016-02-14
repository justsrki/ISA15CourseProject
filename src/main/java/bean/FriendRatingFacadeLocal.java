package bean;

import java.util.List;
import javax.ejb.Local;
import model.FriendRating;

/**
 *
 * @author Srđan
 */
@Local
public interface FriendRatingFacadeLocal {

    void create(FriendRating friendRating);

    void edit(FriendRating friendRating);

    void remove(FriendRating friendRating);

    FriendRating find(Object id);

    List<FriendRating> findAll();

    List<FriendRating> findRange(int[] range);

    int count();
    
}
