package rest;

import javax.ws.rs.core.Response;

/**
 * @author - Srđan Milaković
 */
public class ResponseError {
    private String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public static Response createBadRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ResponseError(message)).build();
    }

    public static Response createUnauthorized(String message) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ResponseError(message)).build();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
