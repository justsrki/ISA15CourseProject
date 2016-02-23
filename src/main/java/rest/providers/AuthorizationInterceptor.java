package rest.providers;

import beans.dao.interfaces.LogLocal;
import beans.dao.interfaces.TokenLocal;
import model.dao.Log;
import model.dao.Token;
import model.dao.User;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import rest.util.BasicResponse;

import javax.annotation.Priority;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author - Srđan Milaković
 */
@Provider
@Priority(1000)
public class AuthorizationInterceptor implements ContainerRequestFilter {

    private static final String PARAM_AUTH_TOKEN = "auth-token";
    private static final String PARAM_AUTH_ID = "auth-id";

    @EJB
    private TokenLocal tokenBean;

    @EJB
    private LogLocal logBean;

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

            Token token = tokenBean.findByValue(authToken);
            if (token == null) {
                abort(requestContext);
                return;
            }

            User user = token.getUserId();
            if (userId == null || !userId.equals(user.getId().toString())) {
                abort(requestContext);
                return;
            }

            if (!rolesAllowed.contains(user.getRole())) {
                abort(requestContext);
            } else {
                ResteasyProviderFactory.pushContext(User.class, user);
            }
        }
    }

    private void abort(ContainerRequestContext requestContext) {
        requestContext.abortWith(BasicResponse.createUnauthorized("Not authorized."));
        Log log = new Log(null, new Date(), requestContext.getMethod(), requestContext.getUriInfo().getPath());
        logBean.create(log);
    }

}
