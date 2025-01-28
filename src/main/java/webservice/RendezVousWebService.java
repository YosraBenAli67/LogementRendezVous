package webservice;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rendezvous/")
public class RendezVousWebService {
    static public RendezVousBusiness rdvb = new RendezVousBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response listRendezVous() {
        return Response.status(200).entity(rdvb.getListeRendezVous()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getRdvByRef(@PathParam(value = "id") int id) {
        RendezVous result = rdvb.getRendezVousById(id);
        if (result != null) {
            return Response.status(200).entity(rdvb.getRendezVousById(id)).build();
        } else {
            return Response.status(400).entity("RendezVous does not exist !").build();
        }
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addRdv(RendezVous rdv) {
        if (rdvb.addRendezVous(rdv)) {
            return Response.status(201).entity("RendezVous created !").build();
        } else {
            return Response.status(409).entity("RendezVous already exists !").build();
        }

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRdv(@PathParam(value = "id") int id) {
        boolean result = rdvb.deleteRendezVous(id);
        if (result) {
            return Response.status(200).entity("RendezVous deleted !").build();
        } else {
            return Response.status(400).entity("RendezVous does not exist !").build();
        }

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRdv(@PathParam(value = "id") int id, RendezVous rdv) {
        boolean result = rdvb.updateRendezVous(id, rdv);
        if (result) {
            return Response.status(200).entity("RendezVous updated !").build();
        } else {
            return Response.status(400).entity("RendezVous does not exist !").build();
        }
    }
}
