package at.htl.autovermietung.boundary;

import at.htl.autovermietung.control.RepositoryCar;
import at.htl.autovermietung.entity.Car;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("api/car")
public class CarService {

    @Inject
    RepositoryCar repo;


    @Operation(
            summary = "Gets all Cars",
            description = "Gets the cars from the repository"
    )

    @Transactional
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars(){
        return Response.ok(repo.getCars()).header("tag","list of cars").build();
    }


    @Transactional
    @GET
    @Path("get-car/{licensePlate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCar(@PathParam("licensePlate") String licensePlate){
        return Response.ok( repo.getCarByLicensePlate(licensePlate)).build();
    }

    @POST
    @Path("add-car")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(JsonObject car) {

        return Response
                .ok("Car: " + repo.addCar(car))
                .build();
    }


    @POST
    @Path("add-multiple-cars")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public Response saveMultiple(List<Car> carList){
        carList.forEach(c -> repo.addCars(c));
        return Response.ok().build();
    }

    @PUT
    @Path("update-car")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(JsonObject car){
        return Response.ok("Updated: " + repo.updateCar(car)).build();
    }

    @Operation(
            summary = "Deletes a Car",
            description = "Deletes a car by searching for license plate"
    )
    @DELETE
    @Path("delete-car/{licensePlate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCar(@PathParam("licensePlate") String licensePlate){
        return Response.ok("Deleted: " + repo.deleteCar(licensePlate)).build();
    }

    @PATCH
    @Path("update-car-price")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarPrice(@QueryParam("licensePlate") String licensePlate, @QueryParam("price") double price){
        return Response.ok("Updated: " + repo.updateCarPrice(licensePlate, price) ).build();
    }

}
