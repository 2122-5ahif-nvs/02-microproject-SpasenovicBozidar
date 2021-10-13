package at.htl.autovermietung.boundary;

import at.htl.autovermietung.entity.Car;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class CarServiceTest {

    @Order(10)
    @Test
    public void t01_TestCarEndpointGet(){
        Car car = new Car("Porsche","911", 250, 200, "L-394LK");
        given()
                .when()
                .body(car)
                .get("api/car")
                .then()
                .log().body()
                .statusCode(202);
    }
    @Order(20)
    @Test
    public void t02_TestCarEndpointGetCarByLicenseplate(){
        var actual = given()
                .when()
                .get("api/car/get-car/L-324LK")
                .then()
                .log().body()
                .statusCode(200).extract()
                .body()
                .xmlPath()
                .getList("", Car.class)
                .get(0);

        assertThat(actual.getLicensePlate()).isEqualTo("L-324LK");

    }
    @Order(30)
    @Test
    public void t03_TestCarEndpointAddCar(){
        Car car = new Car("Kia","Rio", 120, 100, "L-884SK");

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(car)
                .post("api/car/add-car")
                .then()
                .log()
                .body()
                .statusCode(200);
    }
    @Order(40)
    @Test
    public void t04_TestCarEndpointDelete(){
        String actual = given()
                .delete("api/car/delete-car/L-324LK")
                .asString();
        assertThat(actual).isEqualTo("Deleted: true");
    }
}