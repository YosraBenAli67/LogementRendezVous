package webservice;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("logement/")
public class LogementWebService {
    static public LogementBusiness logb= new LogementBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response listLogement() {
        return Response.status(200).entity(logb.getLogements()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{ref}")
    public Response getLogByRef(@PathParam(value = "ref") int ref) {
        return Response
                .status(200)
                .entity(logb.getLogementsByReference(ref))
                .build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addLogement(Logement logement) {
        if (logb.addLogement(logement)) {
            return Response.status(201).entity("Logement created !").build();
        } else {
            return Response.status(409).entity("Logement already exists !").build();
        }

    }

    @DELETE
    @Path("{ref}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam(value = "ref") int ref) {
        boolean result = logb.deleteLogement(ref);
        if (result) {
            return Response.status(200).entity("Logement deleted !").build();
        } else {
            return Response.status(400).entity("Logement does not exist !").build();
        }

    }

    @PUT
    @Path("{ref}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogement(@PathParam(value = "ref") int ref, Logement logement) {
        boolean result = logb.updateLogement(ref, logement);
        if (result) {
            return Response.status(200).entity("Logement updated !").build();
        } else {
            return Response.status(400).entity("Logement does not exist !").build();
        }

    }
}
