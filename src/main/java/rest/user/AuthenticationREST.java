package rest.user;

import beans.data_access.interfaces.UserLocal;
import beans.util.PasswordGeneratorLocal;

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
    private UserLocal user;

    @EJB
    private PasswordGeneratorLocal passwordGenerator;

    @POST
    @Path("/login")
    public void createToken(@Valid AuthenticationRequest data) {
        System.out.println(passwordGenerator.generatePassword());
        System.out.println(data);
        System.out.println(user.find(data.getEmail()));

    }
}
