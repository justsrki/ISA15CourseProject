package providers;

import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author - Srđan Milaković
 */
@Provider
public class NoResultExceptionProvider implements ExceptionMapper<NoResultException> {

    @Override
    public Response toResponse(NoResultException e) {
        return Response.status(404).entity("Resource does not exist.").build();
    }

}
