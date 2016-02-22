package rest.util;

import com.google.gson.Gson;

import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class BasicResponse implements Serializable {
    private String message;

    public BasicResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public static Response createResponse(Response.Status statusCode, String message) {
        return Response.status(statusCode).entity(new BasicResponse(message).toJSON()).build();
    }

    //<editor-fold desc="200 OK">
    public static Response createOk(String message) {
        return createResponse(Response.Status.OK, message);
    }

    public static Response createDeleted() {
        return createResponse(Response.Status.OK, "Deleted.");
    }
    //</editor-fold>

    //<editor-fold desc="201 Created">
    public static Response createCreated() {
        return createCreated("Resource created.");
    }

    public static Response createCreated(String message) {
        return createResponse(Response.Status.CREATED, message);
    }

    public static Response createChanged() {
        return createResponse(Response.Status.CREATED, "Changed.");
    }
    //</editor-fold>

    //<editor-fold desc="400 Bad request">
    public static Response createBadRequest(String message) {
        return createResponse(Response.Status.BAD_REQUEST, message);
    }
    //</editor-fold>

    //<editor-fold desc="401 Unauthorized">
    public static Response createUnauthorized(String message) {
        return createResponse(Response.Status.UNAUTHORIZED, message);
    }

    public static Response createUnauthorized() {
        return createUnauthorized("You don't have permission to access object.");
    }
    //</editor-fold>

    //<editor-fold desc="403 Forbidden">
    public static Response createForbidden(String message) {
        return createResponse(Response.Status.FORBIDDEN, message);
    }

    public static Response createForbidden() {
        return createForbidden("You don't have permission to access object.");
    }
    //</editor-fold>

    //<editor-fold desc="404 Not found">
    public static Response createNotFound(String message) {
        return createResponse(Response.Status.NOT_FOUND, message);
    }

    public static Response createNotFound() {
        return createResponse(Response.Status.NOT_FOUND, "Resource not found.");
    }
    //</editor-fold>

}
