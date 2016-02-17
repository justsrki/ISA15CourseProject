package bean2;

import java.util.List;
import javax.ejb.Local;
import model.dao.Meal;

/**
 *
 * @author Srđan
 */
@Local
public interface MealFacadeLocal {

    void create(Meal meal);

    void edit(Meal meal);

    void remove(Meal meal);

    Meal find(Object id);

    List<Meal> findAll();

    List<Meal> findRange(int[] range);

    int count();
    
}
