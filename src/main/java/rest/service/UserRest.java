package rest.service;

import beans.dao.interfaces.UserLocal;
import model.dao.User;
import model.dto.user.UserDto;
import rest.util.BasicResponse;
import rest.util.ResponseExceptions;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Srđan
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
        List<UserDto> response = new ArrayList<>();
        users.forEach(user -> response.add(new UserDto(user)));
        return response;
    }

    private Object filterUsersCustomer(List<User> users, User user) {
        List<UserDto> response = new ArrayList<>();
        users.forEach(u -> {
            if (User.CUSTOMER.equals(u.getRole()) && u.getActivated() && !u.equals(user)) {
                response.add(new UserDto(u, user.getFollowingSet().contains(u)));
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

        return new UserDto(user);
    }

    @PUT
    @Path("/profile")
    @RolesAllowed({User.CUSTOMER, User.MANAGER, User.ADMINISTRATOR})
    public Object editProfile(@Context User user, UserDto userDto) {
        if (!user.getActivated()) {
            throw ResponseExceptions.createNotFound();
        }

        user = userBean.find(user.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        userBean.edit(user);

        return BasicResponse.createChanged();
    }

    @GET
    @Path("/friend")
    @RolesAllowed(User.CUSTOMER)
    public Object getFriends(@Context User user) {
        List<UserDto> response = new ArrayList<>();
        user.getFollowingSet().forEach(u -> response.add(new UserDto(u, true)));
        return response;
    }

    @POST
    @Path("/friend/{id}")
    @RolesAllowed({User.CUSTOMER})
    public Object followUser(@PathParam("id") Integer id, @Context User user) {
        User followed = userBean.find(id);
        if (followed == null || !User.CUSTOMER.equals(followed.getRole())) {
            throw ResponseExceptions.createNotFound();
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
            throw ResponseExceptions.createNotFound();
        }

        userBean.unfollow(user, followed);

        return BasicResponse.createCreated();
    }
}
