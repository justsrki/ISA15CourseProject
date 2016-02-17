package bean2;

import model.dao.FriendRating;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface FriendRatingLocal {

    void create(FriendRating friendRating);

    void edit(FriendRating friendRating);

    void remove(FriendRating friendRating);

    FriendRating find(Integer id);

    List<FriendRating> findAll();

    List<FriendRating> findRange(int[] range);

    int count();
    
}
