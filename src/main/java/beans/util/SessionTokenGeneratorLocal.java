package beans.util;

import javax.ejb.Local;

/**
 * @author - Srđan Milaković
 */
@Local
public interface SessionTokenGeneratorLocal {
    String generateSessionToken();
}
