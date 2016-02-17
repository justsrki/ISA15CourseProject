package beans.dao.interfaces;

import model.dao.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Srđan
 */
@Local
public interface UserLocal {

    void create(User user);

    void edit(User user);

    void remove(User user);

    User find(Object id);

    User findByEmail(String email);

    List<User> findAll();

    List<User> findRange(int[] range);

    int count();

}