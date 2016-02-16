package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.data_access.AbstractFacade;
import model.Meal;

/**
 *
 * @author Srđan
 */
@Stateless
public class MealFacade extends AbstractFacade<Meal> implements MealFacadeLocal {


    public MealFacade() {
        super(Meal.class);
    }
    
}
