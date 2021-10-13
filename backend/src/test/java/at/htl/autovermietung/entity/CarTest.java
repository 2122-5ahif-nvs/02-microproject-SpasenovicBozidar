package at.htl.autovermietung.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {



    @Test
    void t01_getLicensePlate() {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.getLicensePlate().equals("L-394KK"));
    }

    @Test
    void to2_getModel() {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.getModel().equals("rio"));

    }

    @Test
    void to3_getCost()  {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.getCost() == 50);

    }

    @Test
    void t04_getName()  {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.getName().equals("Kia"));

    }


    @Test
    void t05_getPs()  {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.getPs() == 70);

    }


    @Test
    void t06_getToString() {
        Car car = new Car("Kia", "rio",70,50,"L-394KK");
        assertThat(car.toString().equals( "Kia: \nrio: \n70: \n50: \nL-394KK:"));

    }

}