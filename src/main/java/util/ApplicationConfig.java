package util;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author - Srđan Milaković
 */
@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    public static final String baseUrl = "http://localhost:8080";
}
