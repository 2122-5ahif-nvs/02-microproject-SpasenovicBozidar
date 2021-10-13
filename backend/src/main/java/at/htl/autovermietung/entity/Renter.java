package at.htl.autovermietung.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = "Renter.getRenters",query = "select r from Renter r"),
        @NamedQuery(name = "Renter.getRenterById", query = "select r from Renter r where r.id = :ID"),
        @NamedQuery(name = "Renter.clearEm", query = "delete from Renter")
})
@Entity()
@Table(name = "RENTER")
@XmlRootElement
public class Renter {



    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Renter(String name) {
        this.name = name;
    }

    public Renter() {
    }

    public void setName(String _name) {
        this.name = _name;
    }


    @Override
    public String toString() {
        return "id:" + getId() + "name:" + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renter renter = (Renter) o;
        return id == renter.id &&
                Objects.equals(name, renter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
