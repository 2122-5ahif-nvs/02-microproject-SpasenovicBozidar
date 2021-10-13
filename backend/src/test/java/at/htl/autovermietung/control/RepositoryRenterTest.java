package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Renter;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RepositoryRenterTest {
    @Inject
    RepositoryRenter repositoryRenter;

    @Test
    @Transactional
    public void t01_FirstShouldBeBenjo(){

        assertThat(repositoryRenter.getRenters().get(0).getName()).isEqualTo("Benjo");

    }

    @Test
    @Transactional
    public void t02_ShouldBeCorrectSize(){

        assertThat(repositoryRenter.getRenters().size() == 6);
    }
    @Test
    @Transactional
    public void t03_ShouldBeCorrectSizeAfterAdd(){
        Renter newRenter = new Renter("Boki");
        repositoryRenter.addRentalAsObject(newRenter);
        assertThat(repositoryRenter.getRenters().size() == 7);
    }
    @Test
    @Transactional
    public void t04_ShouldBeCorrectSizeAfterDelete() {
        Renter newRenter = new Renter("Boki");
        repositoryRenter.addRentalAsObject(newRenter);
        assertThat(repositoryRenter.getRenters().size() == 7);

        repositoryRenter.deleteRenter(newRenter.getId());
        assertThat(repositoryRenter.getRenters().size() == 6);

    }
}