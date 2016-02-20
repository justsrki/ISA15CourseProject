package rest.util;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class ResponseExceptions {

    //<editor-fold desc="400 Bad request">
    public static BadRequestException createBadRequest(String message) {
        return new BadRequestException(BasicResponse.createBadRequest(message));
    }
    //</editor-fold>

    //<editor-fold desc="401 Unauthorized">
    public static NotAuthorizedException createUnauthorized() {
        return new NotAuthorizedException(BasicResponse.createUnauthorized());
    }

    public static NotAuthorizedException createUnauthorized(String message) {
        return new NotAuthorizedException(BasicResponse.createUnauthorized(message));
    }
    //</editor-fold>

    //<editor-fold desc="403 Forbidden">
    public static ForbiddenException createForbidden() {
        return new ForbiddenException(BasicResponse.createForbidden());
    }

    public static ForbiddenException createForbidden(String message) {
        return new ForbiddenException(BasicResponse.createForbidden(message));
    }

    //</editor-fold>

    //<editor-fold desc="404 Not found">
    public static NotFoundException createNotFound() {
        return new NotFoundException(BasicResponse.createNotFound());
    }

    public static NotFoundException createNotFound(String message) {
        return new NotFoundException(BasicResponse.createNotFound(message));
    }
    //</editor-fold>

}
