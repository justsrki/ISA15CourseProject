package rest;

import javax.ws.rs.core.Response;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class BasicResponse {
    private String message;

    public BasicResponse(String message) {
        this.message = message;
    }

    public static Response createResponse(Response.Status statusCode, String message) {
        return Response.status(statusCode).entity(new BasicResponse(message)).build();
    }

    public static Response createBadRequest(String message) {
        return createResponse(Response.Status.BAD_REQUEST, message);
    }

    public static Response createUnauthorized(String message) {
        return createResponse(Response.Status.UNAUTHORIZED, message);
    }

    public static Response createCreated() {
        return createCreated("Resource created.");
    }

    public static Response createCreated(String message) {
        return createResponse(Response.Status.CREATED, message);
    }

    public static Response createSuccessful(String message) {
        return createResponse(Response.Status.OK, message);
    }

    public static Response createNotFound(String message) {
        return createResponse(Response.Status.NOT_FOUND, message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
