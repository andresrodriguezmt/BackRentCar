package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.model.Client;
import co.usa.ciclo3.reto5.model.ContadorClientes;
import co.usa.ciclo3.reto5.model.Reservation;
import co.usa.ciclo3.reto5.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
    
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    
    public Optional<Reservation> getReservation(int id){
    
        return reservationCrudRepository.findById(id);
    }
    
    public Reservation save(Reservation reservacion){
        return reservationCrudRepository.save(reservacion);
    }
    
    public void delete(Reservation reservacion){
        reservationCrudRepository.delete(reservacion);
    }
    
    public List<Reservation> reservationStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);             
    }
    
    public List<Reservation> reservationTimeRepository(Date fechaUno, Date fechaDos){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(fechaUno, fechaDos);
    }
    
    public List<ContadorClientes> getClientRepository(){
        
        List<ContadorClientes> res = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationsByClient();
        
        for(int i = 0 ; i < report.size() ; i++){
            
            res.add(new ContadorClientes((Long)report.get(i)[1],(Client)report.get(i)[0]));
            
        }
        
        return res;
    }
}
