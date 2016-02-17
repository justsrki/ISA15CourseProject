package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Log;

import javax.ejb.Local;

/**
 * @author - Srđan Milaković
 */
@Local
public interface LogLocal extends AbstractLocal<Log> {
}
