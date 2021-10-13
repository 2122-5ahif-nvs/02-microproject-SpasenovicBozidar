package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Rental;
import at.htl.autovermietung.entity.Renter;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;

public class InitBean {

    @Inject
    EntityManager em;


   @Transactional
   void onStart(@Observes StartupEvent event) {
       em.persist(new Car("Porsche","911", 250, 200, "L-394LK"));
       em.persist(new Car("Koenigsegg","Jesko", 650, 300, "L-300LK"));
       em.persist(new Car("Kia","Sportage", 140, 60, "UU-152CC"));
       em.persist(new Car("Yugo","45", 50, 20, "LL-899HQ"));
       em.persist(new Car("Fiat","Punto", 60, 30, "L-109VV"));
       em.persist(new Car("Mercedes","E-400", 230, 120, "SR-373MM"));
       em.persist(new Car("Fiat","Punto", 40, 20, "LL-111FK"));
       em.persist(new Car("Mercedes","C63", 270, 170, "L-324LK"));
       em.persist(new Car("Audi","A5", 240, 150, "UU-974OK"));
       em.persist(new Car("Audi","A4", 140, 100, "WE-114GK"));
       em.persist(new Car("Renault","Espace", 140, 50, "WE-314OK"));
       em.persist(new Car("Lada","4x4", 120, 50, "L-764LG"));
       em.persist(new Car("Audi","A4", 120, 50, "L-111FF"));

       em.persist(new Rental( em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "L-394LK").getSingleResult(), new Renter("Boki"), LocalDate.now(), LocalDate.of(2020, 12, 11)));
       em.persist(new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "L-300LK").getSingleResult(), new Renter( "David"), LocalDate.of(2020, 4, 13), LocalDate.of(2020, 5, 13)));
       em.persist(new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "UU-152CC").getSingleResult(), new Renter( "Benjo"), LocalDate.of(2020, 1, 11), LocalDate.of(2020, 2, 11)));
       em.persist(new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "LL-899HQ").getSingleResult(), new Renter("Robi"), LocalDate.of(2020, 3, 14), LocalDate.of(2020, 3, 14)));
       em.persist(new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "L-109VV").getSingleResult(), new Renter( "Yimne"), LocalDate.of(2020, 2, 4), LocalDate.of(2020, 3, 4)));
       em.persist(new Rental(em.createNamedQuery("Car.getCarByLicensePlate", Car.class).setParameter("LICENSEPLATE", "SR-373MM").getSingleResult(), new Renter( "Andricic"), LocalDate.of(2020, 4, 24), LocalDate.of(2020, 5, 24)));


   }


}
