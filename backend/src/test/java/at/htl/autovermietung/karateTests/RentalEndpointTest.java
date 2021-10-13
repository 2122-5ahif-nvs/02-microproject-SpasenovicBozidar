package at.htl.autovermietung.karateTests;


import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
public class RentalEndpointTest {

    @Karate.Test
    Karate t01_Test(){
            return Karate.run("RentalTest.feature").relativeTo(getClass());
    }
}
