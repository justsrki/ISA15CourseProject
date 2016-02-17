package rest.restaurant;

import beans.dao.interfaces.RestaurantLocal;
import model.dao.Restaurant;
import model.dao.User;
import rest.BasicResponse;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jms.ObjectMessage;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author - Srđan Milaković
 */
@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantRest {

    @EJB
    private RestaurantLocal restaurantBean;

    @GET
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object getAll() {
        return null;
    }

    @GET
    @Path("{id}")
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object get(@PathParam("id") Integer id) {
        return null;
    }

    @POST
    @RolesAllowed(User.ADMINISTRATOR)
    public Object create(@Valid RestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setLatitude(request.getLatitude());
        restaurant.setLongitude(request.getLongitude());
        restaurantBean.create(restaurant);

        return BasicResponse.createCreated();
    }

    @PUT
    @Path("{id}")
    @RolesAllowed(User.ADMINISTRATOR)
    public Object edit(@PathParam("id") Integer id, @Valid RestaurantRequest request) {
        return null;
    }


}
