package providers;

import rest.BasicResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author - Srđan Milaković
 */
@Provider
public class NotFoundExceptionProvider implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
