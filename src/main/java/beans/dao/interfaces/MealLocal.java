package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Meal;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Srđan
 */
@Local
public interface MealLocal extends AbstractLocal<Meal> {

}
