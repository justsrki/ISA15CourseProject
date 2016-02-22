package rest.service;

import beans.dao.interfaces.UserLocal;
import model.dao.User;
import rest.util.BasicResponse;
import rest.model.user.UserResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Srđan
 */
@Path("/user")
public class UserRest {

    @EJB
    private UserLocal userBean;

    @GET
    @RolesAllowed({User.ADMINISTRATOR, User.CUSTOMER})
    public Object get(@Context User user) {
        List<User> users = userBean.findAll();

        switch (user.getRole()) {
            case User.ADMINISTRATOR:
                return filterUsersAdministrator(users);
            case User.CUSTOMER:
                return filterUsersCustomer(users, user);
            default:
                return BasicResponse.createNotFound("Role not found.");
        }
    }

    private Object filterUsersAdministrator(List<User> users) {
        List<UserResponse> response = new ArrayList<>();
        users.forEach(user -> response.add(new UserResponse(user)));
        return response;
    }

    private Object filterUsersCustomer(List<User> users, User user) {
        List<UserResponse> response = new ArrayList<>();
        users.forEach(u -> {
            if (User.CUSTOMER.equals(u.getRole()) && u.getActivated() && !u.equals(user)) {
                response.add(new UserResponse(u, user.getFollowingSet().contains(u)));
            }
        });
        return response;
    }

    @GET
    @Path("/profile")
    @RolesAllowed({User.CUSTOMER, User.MANAGER, User.ADMINISTRATOR})
    public Object getProfile(@Context User user) {
        if (!user.getActivated()) {
            throw ResponseExceptions.createNotFound();
        }

        return new UserResponse(user);
    }

    @POST
    @Path("/friend/{id}")
    @RolesAllowed({User.CUSTOMER})
    public Object followUser(@PathParam("id") Integer id, @Context User user) {
        User followed = userBean.find(id);
        if (followed == null || !User.CUSTOMER.equals(followed.getRole())) {
            return ResponseExceptions.createNotFound();
        }

        userBean.follow(user, followed);

        return BasicResponse.createCreated();
    }

    @DELETE
    @Path("/friend/{id}")
    @RolesAllowed({User.CUSTOMER})
    public Object unfollowUser(@PathParam("id") Integer id, @Context User user) {
        User followed = userBean.find(id);
        if (followed == null || !User.CUSTOMER.equals(followed.getRole())) {
            return ResponseExceptions.createNotFound();
        }

        userBean.unfollow(user, followed);

        return BasicResponse.createCreated();
    }
}
