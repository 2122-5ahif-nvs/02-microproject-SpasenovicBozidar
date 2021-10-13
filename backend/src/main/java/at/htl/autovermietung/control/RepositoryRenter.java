package at.htl.autovermietung.control;

import at.htl.autovermietung.entity.Renter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class RepositoryRenter implements PanacheRepository<Renter> {


    public RepositoryRenter() {
    }

    /*@Transactional
    public void insertData(){

        persist(new Renter("Boki"));
        persist(new Renter("David"));
        persist(new Renter("Benjo"));
        persist(new Renter("Yimne" ));
        persist(new Renter("Robi"));
        persist(new Renter("Andricic"));
    }*/
    @Transactional
    public boolean deleteRenter(Long id){
        Renter tempRenter = getRenterById(id);

        if(tempRenter == null){
            return false;
        }

        delete(tempRenter);
        return true;
    }
    @Transactional
    public Renter getRenterById(Long id) {
        return (Renter)list("Renter.getRenterById",id);
    }
    @Transactional
    public List<Renter> getRenters() {
        return Collections.unmodifiableList(list(""));
    }
    @Transactional
    public String addRenter(JsonObject newRenter) {

        persist(new Renter(newRenter.getString("name")));

        return newRenter.toString();
    }

    @Transactional
    public boolean updateRenter(JsonObject newRenter) {
        Renter renter = getRenterById((long) newRenter.getInt("id"));
        if (renter == null)
            return false;

        Renter tempRenter = new Renter(newRenter.getString("name"));
        delete(renter);
        persist(tempRenter);

        return true;
    }

    //Just for Unittest
    @Transactional
    public void addRentalAsObject(Renter rental){
        persist(rental);
    }
    //Just for Unittest
    @Transactional
    public void clearList(){
        list("Renter.clearEm");
    }
}
