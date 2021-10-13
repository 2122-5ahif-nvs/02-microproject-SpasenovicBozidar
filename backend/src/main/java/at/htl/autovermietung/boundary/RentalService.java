package at.htl.autovermietung.boundary;

import at.htl.autovermietung.control.RepositoryRental;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/rental")
public class  RentalService {

    @Inject
    RepositoryRental repo;


    @Operation(
            summary = "Gets all Rentals",
            description = "Gets the rental from the repository"
    )

    @Transactional
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRentals(){
        return Response.ok(repo.getRentals()).header("tag","list of rentals").build();
    }



    @Transactional
    @GET
    @Path("get-rental-by-id/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRentalById(@PathParam("id") Long id){
        return Response.ok( repo.getRentalById(id)).build();
    }

   @POST
    @Path("add-rental")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(JsonObject rental) {

        return Response
                .ok("Rental: " + repo.addRental(rental))
                .build();
    }



    @PUT
    @Path("update-rental")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRental(JsonObject rental){
        return Response.ok("Updated: " + repo.updateRental(rental)).build();
    }


    @DELETE
    @Path("delete-rental-by-id")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRentalById(@QueryParam("id") Long id){
        return Response.ok("Deleted: " + repo.deleteRentalById(id)).build();
    }

}
