package providers;

import beans.dao.interfaces.SessionTokenLocal;
import model.dao.SessionToken;
import model.dao.User;
import rest.BasicResponse;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author - Srđan Milaković
 */
@Provider
public class AuthorizationInterceptor implements ContainerRequestFilter {

    private static final String PARAM_AUTH_TOKEN = "auth-token";
    private static final String PARAM_AUTH_ID = "auth-id";

    @EJB
    SessionTokenLocal sessionTokenBean;

    @Context
    private HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authToken = requestContext.getHeaderString(PARAM_AUTH_TOKEN);
        String userId = requestContext.getHeaderString(PARAM_AUTH_ID);
        Method methodInvoked = resourceInfo.getResourceMethod();

        if (methodInvoked.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAllowedAnnotation = methodInvoked.getAnnotation(RolesAllowed.class);
            Set<String> rolesAllowed = new HashSet<>(Arrays.asList(rolesAllowedAnnotation.value()));

            SessionToken sessionToken = sessionTokenBean.findByValue(authToken);
            if (sessionToken == null) {
                requestContext.abortWith(BasicResponse.createUnauthorized("Not authorized."));
                return;
            }

            User user = sessionToken.getUserId();
            if (userId == null || !userId.equals(user.getId().toString())) {
                requestContext.abortWith(BasicResponse.createUnauthorized("Not authorized."));
                return;
            }

            if (!rolesAllowed.contains(user.getRole())) {
                requestContext.abortWith(BasicResponse.createUnauthorized("Not authorized."));
            } else {
                requestContext.setProperty("user", user);
            }
        }
    }

}
