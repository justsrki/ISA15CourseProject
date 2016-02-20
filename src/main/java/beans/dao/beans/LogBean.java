package beans.dao.beans;

import beans.dao.AbstractBean;
import beans.dao.interfaces.LogLocal;
import model.dao.Log;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Stateless
public class LogBean extends AbstractBean<Log> implements LogLocal {

    public LogBean() {
        super(Log.class);
    }

    @Override
    public List<Log> findAllOrderByDate(int limit) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Log> root = cq.from(Log.class);

        cq.select(root);
        cq.orderBy(cb.desc(root.get("date")));
        return getEntityManager().createQuery(cq).setFirstResult(0).setMaxResults(limit).getResultList();
    }
}
