package beans.dao.local;

import beans.dao.AbstractBean;
import beans.dao.interfaces.LogLocal;
import model.dao.Log;

import javax.ejb.Stateless;

/**
 * @author - Srđan Milaković
 */
@Stateless
public class LogBean extends AbstractBean<Log> implements LogLocal {

    public LogBean() {
        super(Log.class);
    }
}
