package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Rental;
import at.htl.autovermietung.entity.Renter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class RepositoryRental implements PanacheRepository<Rental> {



    @Inject
    RepositoryRenter repositoryRenter;


    @Inject
    RepositoryCar repoCar;


    public RepositoryRental() {


    }

    /*@Transactional
    public void insertData(){
        clearList();
       // repositoryRenter.insertData();
        persist(new Rental( (Car)list("#Car.getCarByLicensePlate", "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11)));
        persist(new Rental((Car)list("Car.getCarByLicensePlate","L-300LK"), new Renter( "David"), LocalDate.of(2020, 4, 13), LocalDate.of(2020, 5, 13)));
        persist(new Rental((Car)list("Car.getCarByLicensePlate", "UU-152CC"), new Renter( "Benjo"), LocalDate.of(2020, 1, 11), LocalDate.of(2020, 2, 11)));
        persist(new Rental((Car)list("Car.getCarByLicensePlate", "LL-899HQ"), new Renter("Robi"), LocalDate.of(2020, 3, 14), LocalDate.of(2020, 3, 14)));
        persist(new Rental((Car)list("Car.getCarByLicensePlate", "L-109VV"), new Renter( "Yimne"), LocalDate.of(2020, 2, 4), LocalDate.of(2020, 3, 4)));
        persist(new Rental((Car)list("Car.getCarByLicensePlate","SR-373MM"), new Renter( "Andricic"), LocalDate.of(2020, 4, 24), LocalDate.of(2020, 5, 24)));

    }*/
    @Transactional
    public boolean deleteRentalById(Long id) {
        Rental tempRental = getRentalById(id);

        if (tempRental == null) {
            return false;
        }

        delete(tempRental);
        return true;
    }

    @Transactional
    public List<Rental> getRentals() {
        return Collections.unmodifiableList(
                list("#Rental.getRentals"));
    }
    @Transactional
    public String addRental(JsonObject newRental) {
        persist(new Rental(repoCar.getCarByLicensePlate(newRental.getJsonObject("car").getString("licensePlate")),
                new Renter( newRental.getJsonObject("renter").getString("name")),
                LocalDate.parse(getDate(newRental.getJsonObject("startDate"))),
                LocalDate.parse(getDate(newRental.getJsonObject("endDate")))));

        return newRental.toString();
    }

    private String getDate(JsonObject date) {
        return date.getInt("year")+"-"+date.getInt("month")+"-"+date.getInt("day");

    }
    @Transactional
    public boolean updateRental(JsonObject newRental) {

        Rental rental = getRentalById(Long.parseLong(newRental.getString("id")));
            if (rental == null)
            return false;

        Rental tempRental = new Rental(repoCar.getCarByLicensePlate(newRental.getJsonObject("car").getString("licensePlate")),
                new Renter(newRental.getJsonObject("renter").getString("name")),
                LocalDate.parse(newRental.getString("startDate")),
                LocalDate.parse(newRental.getString("endDate")));
        delete(rental);
        persist(tempRental);

        return true;
    }


    public Rental getRentalById(Long id) {

        return findById(id);

    }
    public Rental getRentalByLicensePlate(String licensePlate) {
        return (Rental)list("#Rental.getRentalByLicensePlate", licensePlate.toUpperCase());
    }



    //Just for Unittest
    public void addRentalAsObject(Rental rental){
        persist(rental);
    }
    //Just for Unittest
    @Transactional
    public void clearList(){
        list("Rental.clearEm");
    }
}
