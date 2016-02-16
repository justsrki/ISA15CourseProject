package beans.oauth;

import beans.oauth.model.FacebookUserInfo;
import beans.oauth.model.GoogleTokenInfo;
import beans.oauth.model.GoogleUserInfo;

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
