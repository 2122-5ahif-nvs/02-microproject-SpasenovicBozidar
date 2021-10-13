package at.htl.autovermietung.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RenterTest {


    @Test
    void t01_getName(){
        Renter r = new Renter("Boki");
        assertThat(r.getName().equals("Boki"));
    }

    @Test
    void t02_testToString() {
        Renter r = new Renter("Boki");
        assertThat(r.toString().equals("Boki:"));
    }
}