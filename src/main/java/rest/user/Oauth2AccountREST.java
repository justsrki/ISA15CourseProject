package rest.user;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import beans.data_access.interfaces.SessionTokenLocal;
import beans.data_access.interfaces.Oauth2AccountLocal;
import beans.oauth.OAuth2RequestsLocal;
import beans.oauth.model.UserInfo;
import model.Oauth2Account;
import model.SessionToken;
import model.User;
import rest.ResponseError;

/**
 *
 * @author Srđan
 */
@Path("/oauth2")
public class OAuth2AccountREST {

    @EJB
    private SessionTokenLocal sessionTokenBean;
    @EJB
    private Oauth2AccountLocal oauth2AccountBean;
    @EJB
    private OAuth2RequestsLocal oAuth2RequestsBean;

    @POST
    @Path("/login")
    public Object oAuthLogin(@Valid  OAuth2AccountRequest request) {
        UserInfo userInfo;
        String provider;
        if (OAuth2AccountRequest.GOOGLE_PLUS.equals(request.getProvider())) {
            userInfo = oAuth2RequestsBean.googleUserInfo(request.getAccessToken());
            provider = Oauth2Account.GOOGLE;
        } else if (OAuth2AccountRequest.FACEBOOK.equals(request.getProvider())) {
            userInfo = oAuth2RequestsBean.facebookUserInfo(request.getAccessToken());
            provider = Oauth2Account.FACEBOOK;
        } else {
            return ResponseError.createBadRequest("Provider: " + request.getProvider() + " does not exist.");
        }

        if (userInfo == null || userInfo.getId() == null) {
            return ResponseError.createBadRequest("Access token is not valid.");
        }

        Oauth2Account oauth2Account = oauth2AccountBean.findByUserId(userInfo.getId());
        User user = oauth2Account.getCustomerId().getUserId();

        SessionToken sessionToken = null;
        if (oauth2Account == null) {
            sessionToken = oauth2AccountBean.createWithUserInfo(userInfo, provider);
        } else {
            sessionToken = sessionTokenBean.create(user, SessionToken.CUSTOMER);
        }

        return new AuthenticationResponse(User.CUSTOMER, user.getId(), sessionToken.getValue());
    }

}
