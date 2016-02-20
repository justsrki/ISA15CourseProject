package beans.dao.beans;

import javax.ejb.Stateless;

import beans.dao.interfaces.MealLocal;
import beans.dao.AbstractBean;
import model.dao.Meal;

/**
 *
 * @author Srđan
 */
@Stateless
@SuppressWarnings("unused")
public class MealBean extends AbstractBean<Meal> implements MealLocal {

    public MealBean() {
        super(Meal.class);
    }
    
}
