package at.htl.autovermietung.entity;


import at.htl.autovermietung.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.GsonBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "Rental.getRentalById", query = "select r from Rental r where r.id = :ID"),
        @NamedQuery(name = "Rental.getRentalByLicensePlate", query = "select r from Rental r where r.car.licensePlate like :LICENSEPLATE"),
        @NamedQuery(name = "Rental.getRentals", query = "select r from Rental r"),
        @NamedQuery(name = "Rental.clearEm", query = "delete from Rental")
})
@Entity()
@Table(name = "RENTAL")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Rental {


    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.ALL)
    private Renter renter;


    @Column(name = "STARTDATE")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;


    public Long getId() {
        return id;
    }

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "EndDate")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Rental(Car car, Renter renter, LocalDate startDate, LocalDate endDate) {
        this.car = car;
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Rental() {
    }


    public void setCar(Car car) {
        this.car = car;
    }
    public Car getCar() {
        return car;
    }
    public Renter getRenter() {
        return renter;
    }



    public void setRenter(Renter renter) {
        this.renter = renter;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }



    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String asJson(){
        var builder = new GsonBuilder();
        var gson = builder.create();
        //var temp = gson.toJson(this);
        return gson.toJson(this);

    }

    @Override
    public String toString() {
        return
                "car:" + car +", \nrenter:" + renter + ", \nstartDate:" + startDate +", \nendDate:" + endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(car, rental.car) &&
                Objects.equals(renter, rental.renter) &&
                Objects.equals(startDate, rental.startDate) &&
                Objects.equals(endDate, rental.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car, renter, startDate, endDate);
    }
}
