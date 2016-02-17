package bean2;

import javax.ejb.Stateless;

import beans.dao.AbstractBean;
import model.dao.Meal;

/**
 *
 * @author Srđan
 */
@Stateless
public class MealBean extends AbstractBean<Meal> implements MealFacadeLocal {


    public MealBean() {
        super(Meal.class);
    }
    
}
