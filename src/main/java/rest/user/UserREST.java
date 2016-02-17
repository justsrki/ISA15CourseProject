package rest.user;

import beans.dao.interfaces.UserLocal;
import model.dao.User;
import rest.BasicResponse;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
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
                return getAdministrator(users);
            case User.CUSTOMER:
                return getCustomer(users);
            default:
                return BasicResponse.createNotFound("Role not found.");
        }
    }

    private Object getAdministrator(List<User> users) {
        List<UserResponse> response = new ArrayList<>();
        users.forEach(user -> response.add(new UserResponse(user)));
        return response;
    }

    private Object getCustomer(List<User> users) {
        List<UserResponse> response = new ArrayList<>();
        users.forEach(user -> {
            if (User.CUSTOMER.equals(user.getRole()) && user.getActivated()) response.add(new UserResponse(user));
        });
        return response;
    }
}
