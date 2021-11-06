package co.usa.ciclo3.reto5.service;

import co.usa.ciclo3.reto5.model.ContadorClientes;
import co.usa.ciclo3.reto5.model.Reservation;
import co.usa.ciclo3.reto5.model.StatusReservas;
import co.usa.ciclo3.reto5.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 
 * @author gonza
 */
@Service
public class ReservationService {
    
    /**
     * 
     * 
     */
    @Autowired
    private ReservationRepository reservationRepository;
    
    /**
     * 
     * @return 
     */
    public List<Reservation> getAll(){
        return reservationRepository.getAll(); 
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    /**
     * 
     * @param reservacion
     * @return 
     */
    public Reservation save(Reservation reservacion){
        
        if(reservacion.getIdReservation() == null){
            return reservationRepository.save(reservacion);
        }
        else{
            
            Optional<Reservation> varTmp = reservationRepository.getReservation(reservacion.getIdReservation());
            
            if(varTmp.isPresent()){
                
                return reservacion;
            }
            else{
                return reservationRepository.save(reservacion);
            }
            
        
        }
    
    }
    /**
     * 
     * @param reservacion
     * @return 
     */
    public Reservation update(Reservation reservacion){
            
        if(reservacion.getIdReservation() != null ){
            Optional<Reservation> nuevaReserva = reservationRepository.getReservation(reservacion.getIdReservation());
            
            if(nuevaReserva.isPresent()){
                
                if(reservacion.getStartDate() != null){
                    nuevaReserva.get().setStartDate(reservacion.getStartDate());
                }
                
                if(reservacion.getDevolutionDate() != null){
                    nuevaReserva.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                
                return reservationRepository.save(nuevaReserva.get());
            }
        }
        
        return reservacion;
    
    }
    
    /**
     * 
     * @param idReserva
     * @return 
     */
    public boolean delete(int idReserva){
        Boolean verificar = getReservation(idReserva).map( reservacion -> {
            
            reservationRepository.delete(reservacion);
            
            return true;
        }).orElse(false);
        
        return verificar;
    }
    
    /**
     * 
     * @return 
     */
    public StatusReservas getRepSatusRes(){
        List<Reservation> completed = reservationRepository.reservationStatus("completed");
        List<Reservation> cancelled = reservationRepository.reservationStatus("cancelled");
        
        return new StatusReservas(completed.size(), cancelled.size());
        
    }
    
    /**
     * 
     * @param primerDato
     * @param segundoDato
     * @return 
     */
    public List<Reservation> reporteTiempoServicio(String primerDato , String segundoDato){
        
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fechaUno = new Date();
        Date fechaDos = new Date();
        
        try{
            fechaUno = parser.parse(primerDato);
            fechaDos = parser.parse(segundoDato);
        }
        catch(ParseException evt){
            evt.printStackTrace();
        }
        if(fechaUno.before(fechaDos)){
            
            return reservationRepository.reservationTimeRepository(fechaUno, fechaDos);
        
        }
        else{
            return new ArrayList<>();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<ContadorClientes> reporteClientesServicio(){
    
        return reservationRepository.getClientRepository();
    }
}
