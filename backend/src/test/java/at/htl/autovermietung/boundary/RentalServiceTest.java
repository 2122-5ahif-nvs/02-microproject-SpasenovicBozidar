package at.htl.autovermietung.boundary;

import at.htl.autovermietung.control.RepositoryRental;
import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Rental;
import at.htl.autovermietung.entity.Renter;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class RentalServiceTest {


    @Inject
    RepositoryRental repo;

    @Inject
    EntityManager em;

    @Transactional
    @BeforeEach
    public void beforeEach(){

        //repo.clearList();
        //repo.addRentalAsObject(new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11)));
        //repo.addRentalAsObject(new Rental(new Car("Koenigsegg", "Jesko", 650, 300, "L-300LK"), new Renter( "David"), LocalDate.of(2020, 4, 13), LocalDate.of(2020, 5, 13)));

    }

    @Transactional
    @Order(10)
    @Test
    public void t01_TestRentalEndpointGet(){
        Rental rental = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter( "Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        given()
                .when()
                .body(rental)
                .get("api/rental")
                .then()
                .log().body()
                .statusCode(202);
    }


    @Order(20)
    @Test
    @Transactional
    public void t02_TestRentalEndpointGetRentalById(){
        var actual = given()
                .when()
                .get("api/rental/get-rental-by-id/1")
                .then()
                .log().body()
                .statusCode(200).extract()
                .body()
                .jsonPath()
                .getObject("", Rental.class);
        //var hashMap = (LinkedHashMap)actual.get(0);

        //assertThat(actual.getRenter().getId()).isEqualTo(1);

    }
    @Order(30)
    @Test
    @Transactional
    public void t03_TestRentalEndpointAddRental(){
    Rental rental = new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "L-394LK").getSingleResult(), em.createNamedQuery("Renter.getRenterById", Renter.class).setParameter("ID", 1L).getSingleResult(), LocalDate.now(), LocalDate.of(2020, 12, 11));
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(rental.asJson())
                .post("api/rental/add-rental")
                .then()
                .log()
                .body()
                .statusCode(200);
    }
    @Order(40)
    @Test
    @Transactional
    public void t04_TestRentalEndpointDelete(){
        String actual = given()
                .delete("api/rental/delete-rental-by-id/1")
                .asString();
        assertThat(actual).isEqualTo("Deleted: true");
    }
}