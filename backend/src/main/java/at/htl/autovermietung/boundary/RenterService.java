package at.htl.autovermietung.boundary;

import at.htl.autovermietung.control.RepositoryRenter;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/renter")
public class RenterService {

    @Inject
    RepositoryRenter repo;


    @Operation(
            summary = "Gets all Renters",
            description = "Gets the renters from the repository"
    )

    @Transactional
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRenters(){
        return Response.accepted(repo.getRenters()).header("tag","list of renters").build();
    }


    @Transactional
    @GET
    @Path("get-renter/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRenter(@PathParam("id") Long id){
        return Response.ok( repo.getRenterById(id)).build();
    }

    @POST
    @Path("add-renter")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(JsonObject renter) {

        return Response
                .ok("Renter: " + repo.addRenter(renter))
                .build();
    }
    @PUT
    @Path("update-renter")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRenter(JsonObject renter){
        return Response.ok("Updated: " + repo.updateRenter(renter)).build();
    }

    @Operation(
            summary = "Deletes a Renter",
            description = "Deletes a renter by searching for the ID"
    )
    @DELETE
    @Path("delete-renter/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRenter(@PathParam("id") Long id){
        return Response.ok("Deleted: " + repo.deleteRenter(id)).build();
    }
}
