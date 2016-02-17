package bean2;

import model.dao.Meal;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Srđan
 */
@Local
public interface MealLocal {

    void create(Meal meal);

    void edit(Meal meal);

    void remove(Meal meal);

    Meal find(Integer id);

    List<Meal> findAll();

    List<Meal> findRange(int[] range);

    int count();
    
}
