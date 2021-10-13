package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Rental;
import at.htl.autovermietung.entity.Renter;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RepositoryRentalTest {
    @Inject
    RepositoryRental repositoryRental;

    @Test
    @Transactional
    public void t01_FirstShouldBeUU152CC(){

        assertThat(repositoryRental.getRentals().get(0).getCar().getLicensePlate()).isEqualTo("UU-152CC");

    }

    @Test
    @Transactional
    public void t02_ShouldBeCorrectSize(){

        assertThat(repositoryRental.getRentals().size() == 6);
    }

    @Transactional
    @Test
    public void t03_ShouldBeCorrectSizeAfterAdd(){
        Rental newRental = new Rental(new Car("Koenigsegg", "Jesko", 650, 300, "L-811LK"), new Renter("Max"), LocalDate.of(2020, 11, 28), LocalDate.of(2020, 12, 28));
        repositoryRental.addRentalAsObject(newRental);
        assertThat(repositoryRental.getRentals().size() == 7);
    }

    @Test
    @Transactional
    public void t04_ShouldBeCorrectSizeAfterDelete() {
        Rental newRental = new Rental(new Car("Koenigsegg", "Jesko", 650, 300, "L-331LK"), new Renter("Max"), LocalDate.of(2020, 11, 28), LocalDate.of(2020, 12, 28));
        repositoryRental.addRentalAsObject(newRental);
        assertThat(repositoryRental.getRentals().size() == 7);

        repositoryRental.deleteRentalById(newRental.getId());
        assertThat(repositoryRental.getRentals().size() == 6);

    }
}