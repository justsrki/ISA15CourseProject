package rest.restaurant;

import beans.dao.interfaces.RestaurantLocal;
import beans.dao.interfaces.UserLocal;
import model.dao.Restaurant;
import model.dao.User;
import rest.BasicResponse;
import rest.user.CreateManagerRequest;
import rest.user.UserResponse;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestaurantRest {

    @EJB
    private RestaurantLocal restaurantBean;
    @EJB
    private UserLocal userBean;


    @GET
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object getAll(@Context User user) {

        return getAllAdmin();
    }

    @GET
    @Path("{id}")
    @RolesAllowed({User.CUSTOMER, User.ADMINISTRATOR, User.MANAGER})
    public Object get(@PathParam("id") Integer id) {
        if (id == null) {
            return BasicResponse.createBadRequest("Id missing.");
        }

        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            return BasicResponse.createNotFound("Restaurant not found.");
        }

        return new RestaurantResponse(restaurant);
    }


    @GET
    @Path("/{id}/manager")
    @RolesAllowed(User.ADMINISTRATOR)
    public Object getManagers(@PathParam("id") Integer id) {
        if (id == null) {
            return BasicResponse.createBadRequest("Id missing.");
        }

        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            return BasicResponse.createNotFound("Restaurant not found.");
        }

        List<UserResponse> response = new ArrayList<>();
        restaurant.getUserSet().forEach(user -> response.add(new UserResponse(user)));

        return response;
    }

    @POST
    @Path("/{id}/manager")
    @RolesAllowed(User.ADMINISTRATOR)
    public Object createManager(@PathParam("id") Integer id, @Valid CreateManagerRequest managerRequest) {
        if (id == null) {
            return BasicResponse.createBadRequest("Id missing.");
        }
        managerRequest.setRestaurantId(id);

        Restaurant restaurant = restaurantBean.find(id);
        if (restaurant == null) {
            return BasicResponse.createNotFound("Restaurant not found.");
        }

        if (userBean.findByEmail(managerRequest.getEmail()) != null) {
            return BasicResponse.createBadRequest("Email is already used.");
        }

        userBean.create(managerRequest);

        return BasicResponse.createCreated("Manager created,");
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

    private Object getAllCustomer(int userId) {
        return getAllAdmin();
    }

    private Object getAllManager(int managerId) {
        return getAllAdmin();
    }

    private Object getAllAdmin() {
        List<RestaurantResponse> response = new ArrayList<>();
        restaurantBean.findAll().forEach(restaurant -> response.add(new RestaurantResponse(restaurant, 3.5)));
        return response;
    }
}
