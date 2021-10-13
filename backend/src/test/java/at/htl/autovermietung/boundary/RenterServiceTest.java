package at.htl.autovermietung.boundary;

import at.htl.autovermietung.entity.Renter;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import javax.transaction.Transactional;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class RenterServiceTest {



    @Order(10)
    @Test
    public void t01_TestRenterEndpointGet(){
        Renter renter = new Renter("Joko");
        given()
                .when()
                .body(renter)
                .get("api/renter")
                .then()
                .log().body()
                .statusCode(202);
    }
    @Order(20)
    @Test
    @Transactional
    public void t02_TestRenterEndpointGetRenterById(){
        var actual = given()
                .when()
                .get("api/renter/get-renter/6")
                .then()
                .log().body()
                .statusCode(200).extract()
                .body()
                .xmlPath()
                .getList("", Renter.class)
                .get(0);

        assertThat(actual.getName()).isEqualTo("Andricic");

    }
    @Order(30)
    @Test
    public void t03_TestRenterEndpointAddRenter(){
        Renter renter = new Renter("Joko");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(renter)
                .post("api/renter/add-renter")
                .then()
                .log()
                .body()
                .statusCode(200);
    }
    @Order(40)
    @Test
    @Transactional
    public void t04_TestRenterEndpointDelete(){
        String actual = given()
                .delete("api/renter/delete-renter/2")
                .asString();
        assertThat(actual).isEqualTo("Deleted: true");
    }




}