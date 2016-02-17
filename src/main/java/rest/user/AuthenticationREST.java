package rest.user;

import beans.dao.interfaces.CustomerLocal;
import beans.dao.interfaces.Oauth2AccountLocal;
import beans.dao.interfaces.SessionTokenLocal;
import beans.dao.interfaces.UserLocal;
import beans.oauth.OAuth2RequestsLocal;
import model.dao.Oauth2Account;
import model.dao.SessionToken;
import model.dao.User;
import model.oauth.UserInfo;
import rest.BasicResponse;
import util.ApplicationConfig;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author - Srđan Milaković
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationRest {

    @EJB
    private UserLocal userBean;
    @EJB
    private CustomerLocal customerBean;
    @EJB
    private SessionTokenLocal sessionTokenBean;
    @EJB
    private Oauth2AccountLocal oauth2AccountBean;
    @EJB
    private OAuth2RequestsLocal oAuth2RequestsBean;

    @POST
    @Path("/login")
    public Object login(@Valid AuthenticationRequest request) {
        User user = userBean.findByEmail(request.getEmail());
        if (user != null && user.getPassword().equals(request.getPassword()) && user.getActivated()) {
            String sessionToken = sessionTokenBean.create(user, SessionToken.API_ACCESS).getValue();
            return new AuthenticationResponse(user.getRole(), user.getId(), sessionToken);
        } else {
            return BasicResponse.createUnauthorized("Wrong email or password.");
        }
    }

    @GET
    @Path("/accessToken")
    public Object token(@QueryParam("userId") String userId, @QueryParam("accessToken") String accessToken) {
        if (userId == null || accessToken == null) {
            return BasicResponse.createBadRequest("Incomplete request.");
        }

        SessionToken sessionToken = sessionTokenBean.findByValue(accessToken);
        if (sessionToken == null || !SessionToken.API_ACCESS.equals(sessionToken.getType())
                || !userId.equals(sessionToken.getUserId().getId().toString())) {
            return BasicResponse.createUnauthorized("Token is not valid.");
        }

        User user = sessionToken.getUserId();
        return new AuthenticationResponse(user.getRole(), user.getId(), accessToken);
    }

    @POST
    @Path("/signup")
    public Object signup(@Valid SignUpRequest request) {
        User user = userBean.findByEmail(request.getEmail());
        if (user != null) {
            return BasicResponse.createBadRequest("User with email " + request.getEmail() + " already exists!");
        }

        customerBean.create(request);

        return BasicResponse.createCreated("Account created.");
    }

    @GET
    @Path("/activate")
    public Object activate(@QueryParam("token") String token) throws URISyntaxException {
        if (token == null) {
            return BasicResponse.createBadRequest("Activation requires token.");
        }

        SessionToken sessionToken = sessionTokenBean.findByValue(token);
        if (sessionToken == null || !SessionToken.CONFIRM_REGISTRATION.equals(sessionToken.getType())) {
            return BasicResponse.createUnauthorized("Session token is not valid.");
        }

        User user = sessionToken.getUserId();
        if (user.getActivated()) {
            return BasicResponse.createBadRequest("Account is already activated.");
        }

        user.setActivated(true);
        userBean.edit(user);
        return Response.seeOther(new URI(ApplicationConfig.baseUrl + "/login?email=" + user.getEmail())).build();
    }

    @POST
    @Path("/oauth2")
    public Object oAuthLogin(@Valid OAuth2AccountRequest request) {
        /**
         * Get user info from OAuth2 provider
         */
        UserInfo userInfo;
        String provider;
        if (OAuth2AccountRequest.GOOGLE_PLUS.equals(request.getProvider())) {
            userInfo = oAuth2RequestsBean.googleUserInfo(request.getAccessToken());
            provider = Oauth2Account.GOOGLE;
        } else if (OAuth2AccountRequest.FACEBOOK.equals(request.getProvider())) {
            userInfo = oAuth2RequestsBean.facebookUserInfo(request.getAccessToken());
            provider = Oauth2Account.FACEBOOK;
        } else {
            return BasicResponse.createBadRequest("Provider: " + request.getProvider() + " does not exist.");
        }

        if (userInfo == null || userInfo.getId() == null) {
            return BasicResponse.createBadRequest("Access token is not valid.");
        }

        /**
         * If account exists, create new session token
         * Else if account with same email exists, return error
         * Else create new account
         */
        Oauth2Account oauth2Account = oauth2AccountBean.findByUserId(userInfo.getId());
        SessionToken sessionToken;
        if (oauth2Account != null) {
            sessionToken = sessionTokenBean.create(oauth2Account.getCustomerId().getUserId(), SessionToken.API_ACCESS);
        } else if (userBean.findByEmail(userInfo.getEmail()) != null) {
            return BasicResponse.createBadRequest("User with email: " + userInfo.getEmail() + " already exist.");
        } else {
            sessionToken = oauth2AccountBean.createWithUserInfo(userInfo, provider);
        }

        return new AuthenticationResponse(User.CUSTOMER, sessionToken.getUserId().getId(), sessionToken.getValue());
    }

}
