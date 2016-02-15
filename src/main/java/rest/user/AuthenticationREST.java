package rest.user;

import beans.interfaces.UserFacadeLocal;

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
    private UserFacadeLocal user;

    @POST
    @Path("/login")
    public void createToken(@Valid LoginRequest data) {
        System.out.println(data);
        System.out.println(user.find(data.getEmail()));

    }
}
