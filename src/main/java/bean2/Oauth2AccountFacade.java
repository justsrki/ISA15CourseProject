package bean2;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.AbstractFacade;
import model.Oauth2Account;

/**
 *
 * @author Srđan
 */
@Stateless
public class Oauth2AccountFacade extends AbstractFacade<Oauth2Account> implements Oauth2AccountFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Oauth2AccountFacade() {
        super(Oauth2Account.class);
    }
    
}
