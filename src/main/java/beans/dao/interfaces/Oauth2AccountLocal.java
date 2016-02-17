package beans.dao.interfaces;

import beans.dao.AbstractLocal;
import model.dao.Oauth2Account;
import model.dao.Token;
import model.oauth.UserInfo;

import javax.ejb.Local;

/**
 * @author Srđan
 */
@Local
public interface Oauth2AccountLocal extends AbstractLocal<Oauth2Account> {

    Token createWithUserInfo(UserInfo userInfo, String provider);

    Oauth2Account findByUserId(String userId);

}
