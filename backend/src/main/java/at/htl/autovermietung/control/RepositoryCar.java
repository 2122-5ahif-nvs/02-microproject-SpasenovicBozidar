package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Car;
import at.htl.autovermietung.entity.Rental;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@ApplicationScoped
public class RepositoryCar implements PanacheRepository<Car> {




    /*@Transactional
    public void insertData(){
        persist(new Car("Porsche","911", 250, 200, "L-394LK"));
        persist(new Car("Koenigsegg","Jesko", 650, 300, "L-300LK"));
        persist(new Car("Kia","Sportage", 140, 60, "UU-152CC"));
        persist(new Car("Yugo","45", 50, 20, "LL-899HQ"));
        persist(new Car("Fiat","Punto", 60, 30, "L-109VV"));
        persist(new Car("Mercedes","E-400", 230, 120, "SR-373MM"));
        persist(new Car("Fiat","Punto", 40, 20, "LL-111FK"));
        persist(new Car("Mercedes","C63", 270, 170, "L-324LK"));
        persist(new Car("Audi","A5", 240, 150, "UU-974OK"));
        persist(new Car("Audi","A4", 140, 100, "WE-114GK"));
        persist(new Car("Renault","Espace", 140, 50, "WE-314OK"));
        persist(new Car("Lada","4x4", 120, 50, "L-764LG"));
        persist(new Car("Audi","A4", 120, 50, "L-111FF"));
    }*/

    @Transactional
    public boolean deleteCar(String licensePlate){
        Car tempCar = getCarByLicensePlate(licensePlate);

        if(tempCar == null){
            return false;
        }

        delete(tempCar);
        return true;
    }
    public List<Car> getCars() {
        return Collections.unmodifiableList(listAll());
    }

    @Transactional
    public String addCar(JsonObject newCar) {

        persist(new Car(newCar.getString("name"), newCar.getString("model"), newCar.getInt("ps"),  newCar.getInt("cost"), newCar.getString("licensePlate")));

        return newCar.toString();
    }
    @Transactional
    public boolean updateCar(JsonObject newCar){
        Car car = getCarByLicensePlate(newCar.getString("licensePlate"));
        if(car == null)
            return false;

        Car tempCar = new Car(newCar.getString("name"), newCar.getString("model"), newCar.getInt("ps"),  newCar.getInt("cost"), newCar.getString("licensePlate"));
        delete(car);
        persist(tempCar);


        return true;
    }
    @Transactional
    public Car getCarByLicensePlate(String licensePlate){

        //return find("LICENSEPLATE", licensePlate);
        return (Car) getEntityManager()
                .createNamedQuery("Car.getCarByLicensePlate")
                .setParameter("LICENSEPLATE", licensePlate)
                .getSingleResult();
    }

    //Just for Unittest
    @Transactional
    public void addCarAsObject(Car car){
        persist(car);
    }
    //Just for Unittest
    @Transactional
    public void clearList(){
        deleteAll();
    }

    @Transactional
    public boolean addCars(Car car) {
        persist(car);
        return true;
    }


    @Transactional
    public boolean updateCarPrice(String licensePlate, double price) {
        if (find(licensePlate) == null)
            return false;

        Car car = getCarByLicensePlate(licensePlate);
        if(car == null)
            return false;

        Car tempCar = new Car(car.getName(), car.getModel(), car.getPs(),  price, car.getLicensePlate());
        delete(car);
        persist(tempCar);

        return true;
    }
}
