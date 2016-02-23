package rest.service;

import beans.dao.interfaces.InvitationLocal;

import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 * @author - Srđan Milaković
 */
@Path("/invitation")
public class InvitationRest {

    @EJB
    private InvitationLocal invitationBean;


}
