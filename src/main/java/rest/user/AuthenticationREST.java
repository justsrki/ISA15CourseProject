package rest.user;

import beans.data_access.interfaces.SessionTokenLocal;
import beans.data_access.interfaces.UserLocal;
import beans.util.PasswordGeneratorLocal;
import model.SessionToken;
import model.User;
import org.hibernate.hql.internal.ast.ErrorReporter;
import rest.ResponseError;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author - Srđan Milaković
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationREST {

    @EJB
    private UserLocal userBean;
    @EJB
    private SessionTokenLocal sessionTokenBean;


    @POST
    @Path("/login")
    public Object createToken(@Valid AuthenticationRequest data) {
        User user = userBean.findByEmail(data.getEmail());
        if (user.getPassword().equals(data.getPassword())) {
            String sessionToken = sessionTokenBean.create(user, roleToType(user.getRole())).getValue();
            return new AuthenticationResponse(user.getRole(), user.getId(), sessionToken);
        } else {
            return ResponseError.createUnauthorized("Wrong email or password.");
        }
    }

    private String roleToType(String role) {
        switch (role) {
            case User.CUSTOMER:
                return SessionToken.CUSTOMER;
            case User.MANAGER:
                return SessionToken.MANAGER;
            case User.ADMINISTRATOR:
                return SessionToken.ADMINISTRATOR;
            default:
                return null;
        }
    }
}
