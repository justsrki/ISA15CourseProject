package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.SessionToken;

/**
 *
 * @author Srđan
 */
@Stateless
public class SessionTokenFacade extends AbstractFacade<SessionToken> implements SessionTokenFacadeLocal {

    @PersistenceContext(unitName = "GeneratorPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionTokenFacade() {
        super(SessionToken.class);
    }
    
}
