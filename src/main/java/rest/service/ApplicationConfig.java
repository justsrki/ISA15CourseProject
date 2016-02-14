package rest.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Srđan
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(rest.service.CustomerFacadeREST.class);
        resources.add(rest.service.FriendRatingFacadeREST.class);
        resources.add(rest.service.InvitationFacadeREST.class);
        resources.add(rest.service.MealFacadeREST.class);
        resources.add(rest.service.Oauth2AccountFacadeREST.class);
        resources.add(rest.service.ReservationFacadeREST.class);
        resources.add(rest.service.RestaurantFacadeREST.class);
        resources.add(rest.service.SessionTokenFacadeREST.class);
        resources.add(rest.service.TableFacadeREST.class);
        resources.add(rest.service.UserFacadeREST.class);
    }
    
}
