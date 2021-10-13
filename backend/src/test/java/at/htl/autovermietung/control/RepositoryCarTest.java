package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
class RepositoryCarTest {

    @Inject
    RepositoryCar repositoryCar ;

    @Test
    @Transactional
    public void t01_FirstShouldBeL300LK(){
        assertThat(repositoryCar.getCars().get(0).getLicensePlate()).isEqualTo("L-300LK");
    }

    @Test
    @Transactional
    public void t02_ShouldBeCorrectSize(){

        assertThat(repositoryCar.getCars().size() == 13);
    }
    @Test
    @Transactional
    public void t03_ShouldBeCorrectSizeAfterAdd(){
        Car newCar = new Car("Mercedes","C-63", 530, 320, "WE-111MM");
        repositoryCar.addCarAsObject(newCar);
        assertThat(repositoryCar.getCars().size() == 14);
    }
    @Test
    @Transactional
    public void t04_ShouldBeCorrectSizeAfterDelete() {
        Car newCar = new Car("Mercedes", "C-63", 530, 320, "WE-911MM");
        repositoryCar.addCarAsObject(newCar);
        assertThat(repositoryCar.getCars().size() == 14);

        repositoryCar.deleteCar(newCar.getLicensePlate());
        assertThat(repositoryCar.getCars().size() == 13);

    }


}