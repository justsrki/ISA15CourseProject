package providers;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author - Srđan Milaković
 */
@Provider
public class RestMethodLogger implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Class classInvoked = resourceInfo.getResourceClass();
        Method methodInvoked = resourceInfo.getResourceMethod();

        String logString = "";

        if (methodInvoked.isAnnotationPresent(GET.class)) {
            logString += "GET ";
        } else if (methodInvoked.isAnnotationPresent(POST.class)) {
            logString += "POST ";
        } else if (methodInvoked.isAnnotationPresent(PUT.class)) {
            logString += "PUT ";
        } else if (methodInvoked.isAnnotationPresent(DELETE.class)) {
            logString += "DELETE ";
        } else {
            return;
        }

        if (classInvoked.isAnnotationPresent(Path.class)) {
            Path path = (Path) classInvoked.getAnnotation(Path.class);
            logString +=  path.value();
        }

        if (methodInvoked.isAnnotationPresent(Path.class)) {
            Path path = methodInvoked.getAnnotation(Path.class);
            logString += path.value();
        }

        logString = logString.replace("//", "/");

        System.out.println(logString);
    }
}
