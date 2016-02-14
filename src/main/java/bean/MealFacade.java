package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Meal;

/**
 *
 * @author Srđan
 */
@Stateless
public class MealFacade extends AbstractFacade<Meal> implements MealFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MealFacade() {
        super(Meal.class);
    }
    
}
