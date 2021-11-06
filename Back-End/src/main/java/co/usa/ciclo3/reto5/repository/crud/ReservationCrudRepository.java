package co.usa.ciclo3.reto5.repository.crud;

import org.springframework.data.repository.CrudRepository;
import co.usa.ciclo3.reto5.model.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Object>{
    
    public List<Reservation> findAllByStatus(String status);
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateone, Date datetwo);
    
    //SELECT clientid, COUNT(*) AS total FROM reservation group by clientid by desc;
    @Query("SELECT c.client, COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();
}
