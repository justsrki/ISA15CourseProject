package rest.service;

import beans.dao.interfaces.LogLocal;
import model.dao.Log;
import model.dao.User;
import model.dto.log.LogDao;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogRest {

    private static final int MAX_LOGS = 50;

    @EJB
    private LogLocal logBean;

    @GET
    @RolesAllowed(User.ADMINISTRATOR)
    public Object get() {
        List<Log> logs = logBean.findAllOrderByDate(MAX_LOGS);
        List<LogDao> response = new ArrayList<>();

        logs.forEach(log -> {
            Integer userId = log.getUserId() != null ? log.getUserId().getId() : 0;
            response.add(new LogDao(log.getDate(), log.getMethod(), log.getUrl(), userId));
        });

        return response;
    }
}
