package co.usa.ciclo3.reto5.repository;

import co.usa.ciclo3.reto5.model.Gama;
import co.usa.ciclo3.reto5.repository.crud.GamaCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GamaRepository {
    
    @Autowired
    private GamaCrudRepository gamaCrudRepository;
    
    public List<Gama> getAll(){
    
        return (List<Gama>) gamaCrudRepository.findAll();
    }
    
    public Optional<Gama> getGama(int id){
        return gamaCrudRepository.findById(id);
    }
    
    public Gama save(Gama gama){
        return gamaCrudRepository.save(gama);
    }
    
    public void delete(Gama gama){
        gamaCrudRepository.delete(gama);
    }
}
