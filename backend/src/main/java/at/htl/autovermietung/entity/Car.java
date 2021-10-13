package at.htl.autovermietung.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.GsonBuilder;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;




@NamedQueries({
        @NamedQuery(name = "Car.getCars", query = "select c from Car c"),
        @NamedQuery(name = "Car.getCarByLicensePlate", query = "select c from Car c where c.licensePlate = :LICENSEPLATE"),
        @NamedQuery(name = "Car.clearEm", query = "delete from Car")
})
@Entity()
@Table(name = "CAR")
@XmlRootElement
public class Car {

    @XmlElement
    private String carName;



    @Column(name = "PS")
    private double ps;
    @Column(name = "COST")
    private double cost;
    @Column(name = "MODEL")
    private String model;


    @Id
    @Column(name = "LICENSEPLATE")
    private String licensePlate;


    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }



    public Car(String name, String model,double ps, double cost,  String licensePlate) {
        this.carName = name;
        this.ps = ps;
        this.cost = cost;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public Car() {
    }

    public String getName() {
        return carName;
    }

    public void setName(String _name) {
        this.carName = _name;
    }

    public double getPs() {
        return ps;
    }

    public void setPs(double ps) {
        this.ps = ps;
    }

    public String asJson(){
        var builder = new GsonBuilder();
        var gson = builder.create();
        //var temp = gson.toJson(this);
        return gson.toJson(this);

    }
    @Override
    public String toString() {
        return "Name:" + getName()+"\nModel:"+getModel() + "\nPs:" + getPs() + "\nCost:" + getCost() + "\nLicense-Plate:" + getLicensePlate();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.ps, ps) == 0 &&
                Double.compare(car.cost, cost) == 0 &&
                Objects.equals(carName, car.carName) &&
                Objects.equals(model, car.model) &&
                Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carName, ps, cost, model, licensePlate);
    }
}
