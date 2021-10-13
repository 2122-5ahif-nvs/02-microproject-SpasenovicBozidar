package at.htl.autovermietung.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    @Test
    void t01_getCar() {
        Rental r = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        assertThat(r.getCar().toString().equals("Porsche: \n911: \n250: \n200: \nL-394LK:"));
    }

    @Test
    void t02_getRenter()  {
        Rental r = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        assertThat(r.getRenter().toString().equals("1: Boki:"));
    }

    @Test
    void t03_getStartDate()  {
        Rental r = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        assertThat(r.getStartDate().equals(LocalDate.now()));
    }

    @Test
    void t04_getEndDate()  {
        Rental r = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        assertThat(r.getStartDate().equals(LocalDate.of(2020, 12, 11)));
    }

    @Test
    void t05_ToString()  {
        Rental r = new Rental(new Car("Porsche", "911", 250, 200, "L-394LK"), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11));
        assertThat(r.toString().equals("car:" + "Porsche: \n911: \n250: \n200: \nL-394LK:" +", \nrenter:" + "1: Boki:" + ", \nstartDate:" + LocalDate.now() +", \nendDate:" + LocalDate.of(2020, 12, 11)));
    }
}