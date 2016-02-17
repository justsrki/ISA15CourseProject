package beans.oauth;

import model.oauth.FacebookUserInfo;
import model.oauth.GoogleTokenInfo;
import model.oauth.GoogleUserInfo;

import javax.ejb.Local;

/**
 * @author - Srđan Milaković
 */
@Local
public interface OAuth2RequestsLocal {
    GoogleTokenInfo googleTokenInfo(String accessToken);
    GoogleUserInfo googleUserInfo(String accessToken);
    FacebookUserInfo facebookUserInfo(String accessToken);
}
