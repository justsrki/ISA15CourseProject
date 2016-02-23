package rest.providers;

import beans.dao.interfaces.LogLocal;
import model.dao.Log;
import model.dao.User;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author - Srđan Milaković
 */
@Provider
@Priority(1100)
public class RestMethodLogger implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @EJB
    private LogLocal logBean;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Class classInvoked = resourceInfo.getResourceClass();
        Method methodInvoked = resourceInfo.getResourceMethod();

        String method;
        String restUrl = "";

        if (methodInvoked.isAnnotationPresent(GET.class)) {
            method = "GET";
        } else if (methodInvoked.isAnnotationPresent(POST.class)) {
            method = "POST";
        } else if (methodInvoked.isAnnotationPresent(PUT.class)) {
            method = "PUT";
        } else if (methodInvoked.isAnnotationPresent(DELETE.class)) {
            method = "DELETE";
        } else {
            return;
        }

        if (classInvoked.isAnnotationPresent(Path.class)) {
            Path path = (Path) classInvoked.getAnnotation(Path.class);
            restUrl +=  path.value();
        }

        if (methodInvoked.isAnnotationPresent(Path.class)) {
            Path path = methodInvoked.getAnnotation(Path.class);
            restUrl += path.value();
        }

        restUrl = restUrl.replace("//", "/");
        System.out.println(method + " " + restUrl + " " );

        Log log = new Log(null, new Date(), method, requestContext.getUriInfo().getAbsolutePath().getPath());
        log.setUserId(ResteasyProviderFactory.getContextData(User.class));
        logBean.create(log);
    }
}
